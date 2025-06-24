package com.pos.kuppiya.point_of_sale.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_details")
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetails {
    @Id
    @Column(name = "order_detail_id", length = 45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderDetailId;

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private Order orders;

    @Column(name = "item_name", length = 180, nullable = false)
    private String itemName;

    @Column(name = "selling_price", length = 180, nullable = false)
    private double sellingPrice;

    @Column(name = "qty", length = 180, nullable = false)
    private double qty;

    @Column(name = "amount",nullable = false)
    private double amount;

    @ManyToOne
    @JoinColumn(name="item_id", nullable=false)
    private Item items;
}
