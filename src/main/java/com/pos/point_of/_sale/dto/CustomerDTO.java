package com.pos.point_of._sale.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {

    private int customerId;
    private String customerName;
    private String customerAddress;
    private double customerSalary;
    private ArrayList<String> contactNumbers;
    private String nic;
    private boolean activeState;

    public CustomerDTO(String customerName) {
        this.customerName = customerName;
    }
}
