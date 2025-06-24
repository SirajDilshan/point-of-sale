package com.pos.kuppiya.point_of_sale.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseActiveCustomerDTO {
    private String customerName;
    private ArrayList contactNumbers;
}
