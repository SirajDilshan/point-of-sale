package com.pos.point_of._sale.service;

import com.pos.point_of._sale.dto.CustomerDTO;
import com.pos.point_of._sale.dto.request.CustomerSaveRequestDTO;
import com.pos.point_of._sale.dto.request.CustomerUpdateRequestDTO;
import com.pos.point_of._sale.exception.NotFoundException;

import java.util.List;

public interface CustomerService {


    boolean deleteCustomer(int id) throws NotFoundException;

    String addCustomer(CustomerSaveRequestDTO customerSaveRequestDTO);

    String updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO);

    CustomerDTO getCustomerById(int id);

    List<CustomerDTO> getAllCustomers();

    List<CustomerDTO> getByName(String customerName) throws NotFoundException;
}
