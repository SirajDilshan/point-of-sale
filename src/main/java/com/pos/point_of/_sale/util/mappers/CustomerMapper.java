package com.pos.point_of._sale.util.mappers;

import com.pos.point_of._sale.dto.CustomerDTO;
import com.pos.point_of._sale.dto.response.ResponseActiveCustomerDTO;
import com.pos.point_of._sale.dto.response.ResponseCustomerFilterDTO;
import com.pos.point_of._sale.entity.Customer;
import org.mapstruct.Mapper;



import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO entityToDto(Customer customer);
    List<CustomerDTO> entityListToDtoList(List<Customer> customers);

    ResponseActiveCustomerDTO entityToResponseActiveCustomerDTO(Customer customer);
    List<ResponseActiveCustomerDTO> entityListToDtoListOnlyName(List<Customer> customers);


    ResponseCustomerFilterDTO entityToResponseDto(Customer customer);
}
