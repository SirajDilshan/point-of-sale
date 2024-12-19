package com.pos.point_of._sale.service;

import com.pos.point_of._sale.dto.CustomerDTO;
import com.pos.point_of._sale.dto.request.CustomerSaveRequestDTO;
import com.pos.point_of._sale.dto.request.CustomerUpdateRequestDTO;

import java.util.List;

public interface CustomerService {




    String addCustomer(CustomerSaveRequestDTO customerSaveRequestDTO);

    String updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO);

    CustomerDTO getCustomerById(int id);

    List<CustomerDTO> getAllCustomers();
}
