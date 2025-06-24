package com.pos.kuppiya.point_of_sale.service.impl;

import com.pos.kuppiya.point_of_sale.dto.CustomerDTO;
import com.pos.kuppiya.point_of_sale.dto.request.CustomerNameSalNicUpdateQueryRequestDTO;
import com.pos.kuppiya.point_of_sale.dto.request.CustomerSaveRequestDTO;
import com.pos.kuppiya.point_of_sale.dto.request.CustomerUpdateQueryRequestDTO;
import com.pos.kuppiya.point_of_sale.dto.request.CustomerUpdateRequestDTO;
import com.pos.kuppiya.point_of_sale.dto.response.ResponseActiveCustomerDTO;

import com.pos.kuppiya.point_of_sale.dto.response.ResponseAdderAndSalCustomerDTO;
import com.pos.kuppiya.point_of_sale.entity.Customer;
import com.pos.kuppiya.point_of_sale.entity.Order;
import com.pos.kuppiya.point_of_sale.exception.EntryDuplicateException;
import com.pos.kuppiya.point_of_sale.exception.NotFoundException;
import com.pos.kuppiya.point_of_sale.repo.CustomerRepo;
import com.pos.kuppiya.point_of_sale.repo.OrderRepo;
import com.pos.kuppiya.point_of_sale.service.CustomerService;
import com.pos.kuppiya.point_of_sale.util.mappers.CustomerMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public String addCustomer(CustomerSaveRequestDTO customerSaveRequestDTO) {
        Customer customer = new Customer(

                customerSaveRequestDTO.getCustomerName(),
                customerSaveRequestDTO.getCustomerAddress(),
                customerSaveRequestDTO.getCustomerSalary(),
                customerSaveRequestDTO.getContactNumbers(),
                customerSaveRequestDTO.getNic(),
                false
        );
        if (!customerRepo.existsById(customer.getCustomerId())) {
            customerRepo.save(customer);
            return customer.getCustomerName() + " saved";
        } else {
            return "Customer already exists";
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

            return customerRepo.save(customer).getCustomerName() + " updated";
        } else {
//            System.out.println("Customer not found");
//            return "Customer not found";
            throw new EntryDuplicateException("Not in database");
        }
    }

    @Override
    public CustomerDTO getCustomerById(int id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
            //CustomerDTO customerDTO = modelMapper.map(customer.get(), CustomerDTO.class);
            CustomerDTO customerDTO = customerMapper.entityToDto(customer.get());
            return customerDTO;
        } else {
            return null;
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> getCustomers = customerRepo.findAll();
        List<CustomerDTO> customerDTOS = modelMapper.map(getCustomers, new TypeToken<List<CustomerDTO>>() {
        }.getType());
        return customerDTOS;
    }

    @Override
    public boolean deleteCustomer(int id) {
        if (customerRepo.existsById(id)) {

//            List<Order> orders = orderRepo.findByCustomer_CustomerId(id);
//            orderRepo.deleteAll(orders);
//
//            // Then, delete the customer
            customerRepo.deleteById(id);
        } else {
            throw new NoSuchElementException("Not found customer for this id");
        }
        return true;
    }

    @Override
    public List<CustomerDTO> getByName(String customerName) {
        List<Customer> customers = customerRepo.findAllByCustomerNameEquals(customerName);
        if (customers.size() != 0) {
            List<CustomerDTO> customerDTOS = modelMapper.map(customers, new TypeToken<List<CustomerDTO>>() {
            }.getType());
            return customerDTOS;


        } else {
            throw new NoSuchElementException("Not found customer for this name");
        }
    }

    @Override
    public List<CustomerDTO> getCustomerByActiveState() {
        List<Customer> customers = customerRepo.findAllByActiveStateEquals(true);
        if(customers.size() != 0){
            List<CustomerDTO> customerDTOS = customerMapper.entityListToDTOList(customers);
            return customerDTOS;
        }else {
            throw new NoSuchElementException("Not found customer for active state");
        }
    }

    @Override
    public List<ResponseActiveCustomerDTO> getCustomerByActiveStateAndName() {
        List<Customer> customers = customerRepo.findAllByActiveStateEquals(false);
        if(customers.size() != 0){
            List<ResponseActiveCustomerDTO> customerDTOS = customerMapper.entityListToDTOListOnlyName(customers);
            return customerDTOS;
        }else {
            throw new NoSuchElementException("Not found customer for active state");
        }
    }

    @Override
    public String updateCustomerByQuery(CustomerUpdateQueryRequestDTO customerUpdateQueryRequestDTO,int id) {
        if(customerRepo.existsById(id)) {
            Customer customer = customerRepo.getReferenceById(id);
            customerRepo.updateCustomerByQuery(customerUpdateQueryRequestDTO.getCustomerName(),customerUpdateQueryRequestDTO.getNic(),id);
            return "updated "+id;
        }else{
            return "Customer not found "+id;
        }
    }

    @Override
    public CustomerDTO getCustomerByNic(String nic) {
        Optional<Customer> customer = customerRepo.findByNic(nic);
        if (customer.isPresent()) {
            CustomerDTO customerDTO = customerMapper.entityToDto(customer.get());
            return customerDTO;
        } else {
            throw new NotFoundException("not found");
        }
    }



    @Override
    public String updateCustomerNameSalNicByQuery(CustomerNameSalNicUpdateQueryRequestDTO customerNameSalNicUpdateQueryRequestDTO, int id) {
        if(customerRepo.existsById(id)) {
            Customer customer = customerRepo.getReferenceById(id);
            customerRepo.updateCustomerNameSalNicByQuery(customerNameSalNicUpdateQueryRequestDTO.getCustomerName(),customerNameSalNicUpdateQueryRequestDTO.getNic(),customerNameSalNicUpdateQueryRequestDTO.getCustomerSalary(),id);
            return "updated "+id;
        }else{
            return "Customer not found "+id;
        }
    }

    @Override
    public ResponseAdderAndSalCustomerDTO getCustomerSalAndAdderById(int id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
            ResponseAdderAndSalCustomerDTO responseAdderAndSalCustomerDTO = customerMapper.entityAdderAndSalCustomerDTO(customer.get());
            return responseAdderAndSalCustomerDTO;
        } else {
            throw new NotFoundException("Not found customer for this id");
        }
    }

    @Override
    public CustomerDTO getCustomerByIDIfFalse(int id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
            if(customer.get().isActiveState()==false) {
                CustomerDTO customerDTO = customerMapper.entityToDto(customer.get());
                return customerDTO;
            }
            else {
                System.out.println("This is an active state");
            }
        }else {
            throw new NoSuchElementException("Not found customer for this id");
        }
        return null;
    }

    @Override
    public int getActiveCustomerCount() {
        return customerRepo.countByActiveState(true);
    }

    @Override
    public int getInactiveCustomerCount() {
        return customerRepo.countByActiveState(false);
    }


}

