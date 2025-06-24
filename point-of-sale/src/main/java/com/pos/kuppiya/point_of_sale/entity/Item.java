package com.pos.kuppiya.point_of_sale.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pos.kuppiya.point_of_sale.entity.enums.MeasuringUnitTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "Item")
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    @Id
    @Column(name = "item_id", length = 45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;

    @Column(name = "item_name", length = 180, nullable = false)
    private String itemName;

    @Enumerated(EnumType.STRING)
    @Column(name = "measuring_unit", length = 180, nullable = false)
    private MeasuringUnitTypes measuringUnit;

    @Column(name = "balance_qty", length = 180, nullable = false)
    private double balanceQty;

    @Column(name = "supplier_price", length = 180, nullable = false)
    private double supplierPrice;

    @Column(name = "selling_price", length = 180, nullable = false)
    private double sellingPrice;

    @Column(name = "active_state",columnDefinition = "TINYINT default 1")
    private boolean activeState;

    @OneToMany(mappedBy="items")
    private Set<OrderDetails> orderDetails;

}
