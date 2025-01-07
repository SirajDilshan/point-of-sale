package com.pos.point_of._sale.util.mappers;

import com.pos.point_of._sale.dto.CustomerDTO;
import com.pos.point_of._sale.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO entityToDto(Customer customer);
}
