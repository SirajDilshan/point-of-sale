package com.pos.point_of._sale.dto.request;

import lombok.*;

import java.util.ArrayList;


@AllArgsConstructor
@NoArgsConstructor
//@Getter
//@Setter
//@ToString
@Data
public class CustomerUpdateRequestDTO {
    private int customerId;
    private String customerName;
    private String customerAddress;
    private double customerSalary;
    private ArrayList<String> contactNumbers;
    private String nic;
    private boolean activeState;
}
