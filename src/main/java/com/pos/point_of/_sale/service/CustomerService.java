package com.pos.point_of._sale.service;

import com.pos.point_of._sale.dto.CustomerDTO;
import com.pos.point_of._sale.dto.request.CustomerSaveRequestDTO;
import com.pos.point_of._sale.dto.request.CustomerUpdateQueryRequestDTO;
import com.pos.point_of._sale.dto.request.CustomerUpdateRequestDTO;
import com.pos.point_of._sale.dto.response.CustomerUpdateByDTO;
import com.pos.point_of._sale.dto.response.ResponseActiveCustomerDTO;
import com.pos.point_of._sale.dto.response.ResponseCustomerFilterDTO;
import com.pos.point_of._sale.exception.NotFoundException;

import java.util.List;

public interface CustomerService {


    boolean deleteCustomer(int id) throws NotFoundException;

    String addCustomer(CustomerSaveRequestDTO customerSaveRequestDTO);

    String updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO);

    CustomerDTO getCustomerById(int id);

    List<CustomerDTO> getAllCustomers();

    List<CustomerDTO> getByName(String customerName) throws NotFoundException;

    List<CustomerDTO> getAllCustomersByActiveState() throws NotFoundException;


    List<ResponseActiveCustomerDTO> getAllCustomersByActiveStateOnlyName() throws NotFoundException;

    String updateCustomerByQuery(CustomerUpdateQueryRequestDTO customerUpdateQueryRequestDTO,int id);

    CustomerDTO getCustomerByNic(String nic);

    ResponseCustomerFilterDTO getCustomerByIdByFilter(int id);

    String updateCustomerByRequest(CustomerUpdateByDTO customerUpdateByDTO, int id);

    CustomerDTO getCustomerByIdIsActive(int id);
}
