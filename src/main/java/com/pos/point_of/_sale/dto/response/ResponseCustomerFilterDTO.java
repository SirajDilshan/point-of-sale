package com.pos.point_of._sale.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseCustomerFilterDTO {
    private int customerId;
    private String customerAddress;
    private double customerSalary;
}
