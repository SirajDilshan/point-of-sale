package com.pos.kuppiya.point_of_sale.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailRequestDTO {
    private String itemName;
    private double qty;
    private double amount;
    private int items;
}
