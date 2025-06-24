package com.pos.kuppiya.point_of_sale.util.mappers;

import com.pos.kuppiya.point_of_sale.dto.CustomerDTO;
import com.pos.kuppiya.point_of_sale.dto.response.ResponseActiveCustomerDTO;

import com.pos.kuppiya.point_of_sale.dto.response.ResponseAdderAndSalCustomerDTO;
import com.pos.kuppiya.point_of_sale.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;


@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO entityToDto(Customer customer);
    List<CustomerDTO> entityListToDTOList(List<Customer> customers);
    List<ResponseActiveCustomerDTO> entityListToDTOListOnlyName(List<Customer> customers);

    ResponseAdderAndSalCustomerDTO entityAdderAndSalCustomerDTO(Customer customer);
}
