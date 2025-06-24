package com.pos.kuppiya.point_of_sale.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ResponseAdderAndSalCustomerDTO {
    private int customerId;
    private String customerAddress;
    private double customerSalary;
}
