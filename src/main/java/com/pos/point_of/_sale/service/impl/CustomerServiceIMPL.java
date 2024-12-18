package com.pos.point_of._sale.service.impl;

import com.pos.point_of._sale.dto.CustomerDTO;
import com.pos.point_of._sale.dto.request.CustomerSaveRequestDTO;
import com.pos.point_of._sale.entity.Customer;
import com.pos.point_of._sale.repository.CustomerRepo;
import com.pos.point_of._sale.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceIMPL  implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;
    @Override
    public String addCustomer(CustomerSaveRequestDTO customerSaveRequestDTO) {

        Customer customer =new Customer(

                customerSaveRequestDTO.getCustomerName(),
                customerSaveRequestDTO.getCustomerAddress(),
                customerSaveRequestDTO.getCustomerSalary(),
                customerSaveRequestDTO.getContactNumbers(),
                customerSaveRequestDTO.getNic(),
                true
        );
        if(!customerRepo.existsById(customer.getCustomerId())){
            customerRepo.save(customer);
            return customer.getCustomerName()+"saved";
        }else {
            System.out.println("customer id already exist");
            return "customer id already exist";
        }



    }
}
