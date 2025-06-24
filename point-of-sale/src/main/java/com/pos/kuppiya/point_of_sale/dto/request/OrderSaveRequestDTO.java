package com.pos.kuppiya.point_of_sale.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderSaveRequestDTO {
    private int customer;
    private Date orderDate;
    private double total;
    private boolean activeState;
    private List<OrderDetailRequestDTO> orderDetails;
}
