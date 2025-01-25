package com.pos.point_of._sale.dto.request;

import com.pos.point_of._sale.entity.Item;
import com.pos.point_of._sale.entity.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderDetailsSave {



    private String itemName;


    private double Qty;

    private Double amount;

    private int orders;

    private int items;

}
