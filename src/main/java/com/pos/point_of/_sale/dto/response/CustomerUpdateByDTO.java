package com.pos.point_of._sale.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerUpdateByDTO {
    private String customerName;
    private double customerSalary;
    private String nic;
}
