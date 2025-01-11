package com.pos.point_of._sale.service.impl;

import com.pos.point_of._sale.dto.CustomerDTO;
import com.pos.point_of._sale.dto.request.CustomerSaveRequestDTO;
import com.pos.point_of._sale.dto.request.CustomerUpdateQueryRequestDTO;
import com.pos.point_of._sale.dto.request.CustomerUpdateRequestDTO;
import com.pos.point_of._sale.dto.response.CustomerUpdateByDTO;
import com.pos.point_of._sale.dto.response.ResponseActiveCustomerDTO;
import com.pos.point_of._sale.dto.response.ResponseCustomerFilterDTO;
import com.pos.point_of._sale.entity.Customer;
import com.pos.point_of._sale.exception.EntryDuplicationException;
import com.pos.point_of._sale.exception.NotFoundException;
import com.pos.point_of._sale.repository.CustomerRepo;
import com.pos.point_of._sale.service.CustomerService;
import com.pos.point_of._sale.util.mappers.CustomerMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CustomerMapper customerMapper;


    @Override
    public boolean deleteCustomer(int id) throws NotFoundException {
        if (customerRepo.existsById(id)) {
            customerRepo.deleteById(id);
        } else {
            throw new NotFoundException("not found customer for this id");
        }
        return true;
    }

    @Override
    public String addCustomer(CustomerSaveRequestDTO customerSaveRequestDTO) {

        Customer customer = new Customer(

                customerSaveRequestDTO.getCustomerName(), customerSaveRequestDTO.getCustomerAddress(), customerSaveRequestDTO.getCustomerSalary(), customerSaveRequestDTO.getContactNumbers(), customerSaveRequestDTO.getNic(), false);
        if (!customerRepo.existsById(customer.getCustomerId())) {
            customerRepo.save(customer);
            return customer.getCustomerName() + "saved";
        } else {
            System.out.println("customer id already exist");
            return "customer id already exist";
        }


    }

    @Override
    public String updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        if (customerRepo.existsById(customerUpdateRequestDTO.getCustomerId())) {
            Customer customer = customerRepo.getReferenceById(customerUpdateRequestDTO.getCustomerId());
            customer.setCustomerName(customerUpdateRequestDTO.getCustomerName());
            customer.setCustomerAddress(customerUpdateRequestDTO.getCustomerAddress());
            customer.setCustomerSalary(customerUpdateRequestDTO.getCustomerSalary());
            customer.setContactNumbers(customerUpdateRequestDTO.getContactNumbers());
            customer.setNic(customerUpdateRequestDTO.getNic());
            customer.setActiveState(customerUpdateRequestDTO.isActiveState());
            return customerRepo.save(customer).getCustomerName() + "updated";
        } else {
//            System.out.println("this customer not in database");
//            return "this customer not in database";
            throw new EntryDuplicationException("Not In Database");
        }

    }

    @Override
    public CustomerDTO getCustomerById(int id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
            //         CustomerDTO customerDTO = new CustomerDTO(
//                    customer.get().getCustomerId(),
//                    customer.get().getCustomerName(),
//                    customer.get().getCustomerAddress(),
//                    customer.get().getCustomerSalary(),
//                    customer.get().getContactNumbers(),
//                    customer.get().getNic(),
//                    customer.get().isActiveState()
//            );
//            return customerDTO;
//            CustomerDTO customerDTO = modelMapper.map(customer.get(), CustomerDTO.class);
            CustomerDTO customerDTO = customerMapper.entityToDto(customer.get());
            return customerDTO;


        } else {
            return null;
        }

    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> getCustomers = customerRepo.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
//        for(Customer c:getCustomers){
//            CustomerDTO customerDTO = new CustomerDTO(
//                    c.getCustomerId(),
//                    c.getCustomerName(),
//                    c.getCustomerAddress(),
//                    c.getCustomerSalary(),
//                    c.getContactNumbers(),
//                    c.getNic(),
//                    c.isActiveState()
//            );
//            customerDTOList.add(customerDTO);
//
//        }
        List<CustomerDTO> customerDTOS = modelMapper.map(getCustomers, new TypeToken<List<CustomerDTO>>() {
        }.getType());
        return customerDTOS;
    }

    @Override
    public List<CustomerDTO> getByName(String customerName) throws NotFoundException {
        List<Customer> customers = customerRepo.findAllByCustomerNameEquals(customerName);
        if(customers.size()!= 0){
            List<CustomerDTO> customerDTOS = modelMapper.map(customers, new TypeToken<List<CustomerDTO>>() {
            }.getType());
            return customerDTOS;
        }else {
            throw new NotFoundException("not result");

        }

    }

    @Override
    public List<CustomerDTO> getAllCustomersByActiveState() throws NotFoundException {
        List<Customer> customers = customerRepo.findAllByActiveStateEquals(true);
        if(customers.size() !=0){
            List<CustomerDTO> customerDTOS = customerMapper.entityListToDtoList(customers);
            return customerDTOS;
        }
        else {
            throw new NotFoundException("no active customer found");
        }
    }

    @Override
    public List<ResponseActiveCustomerDTO> getAllCustomersByActiveStateOnlyName() throws NotFoundException {
        List<Customer> customers = customerRepo.findAllByActiveStateEquals(true);
        if(customers.size() !=0){
            List<ResponseActiveCustomerDTO> customerDTOS = customerMapper.entityListToDtoListOnlyName(customers);
            return customerDTOS;
        }
        else {
            throw new NotFoundException("no active customer found");
        }
    }

    @Override
    public String updateCustomerByQuery(CustomerUpdateQueryRequestDTO customerUpdateQueryRequestDTO,int id) {
        if(customerRepo.existsById(id)){
           customerRepo.updateCustomerQueryById(customerUpdateQueryRequestDTO.getCustomerName(),customerUpdateQueryRequestDTO.getNic(),id);
            return "update successful id "+ id          ;
        }else {
            System.out.println("no Customer found for this id "+ id);
            return "no Customer found for this id "+ id;
        }

    }

    @Override
    public CustomerDTO getCustomerByNic(String nic) {
        Optional<Customer> customer = customerRepo.findByNicEquals(nic);
        if(customer.isPresent()){
            CustomerDTO customerDTO = modelMapper.map(customer.get(),CustomerDTO.class);
            return customerDTO;
        }else {
            throw new  NotFoundException("not found");
        }

    }

    @Override
    public ResponseCustomerFilterDTO getCustomerByIdByFilter(int id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {

            ResponseCustomerFilterDTO responseCustomerFilterDTO = customerMapper.entityToResponseDto(customer.get());
            return responseCustomerFilterDTO;


        } else {
            throw new  NotFoundException("Not Found");
        }
    }

    @Override
    public String updateCustomerByRequest(CustomerUpdateByDTO customerUpdateByDTO, int id) {
        if (customerRepo.existsById(id)) {
            Customer customer = customerRepo.getReferenceById(id);
            customer.setCustomerName(customerUpdateByDTO.getCustomerName());

            customer.setCustomerSalary(customerUpdateByDTO.getCustomerSalary());

            customer.setNic(customerUpdateByDTO.getNic());

            return customerRepo.save(customer).getCustomerName() + "updated success "+id;
        } else {
//            System.out.println("this customer not in database");
//            return "this customer not in database";
            throw new EntryDuplicationException("Not In Database");
        }
    }


}
