package com.pos.kuppiya.point_of_sale.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseOrderDetailsDTO {
    private String customerName;
    private String customerAddress;
    private ArrayList contactNumbers;
    private Date orderDate;
    private double total;
}
