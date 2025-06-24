package com.pos.kuppiya.point_of_sale.dto.request;

import com.pos.kuppiya.point_of_sale.entity.enums.MeasuringUnitTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemUpdateRequestDTO {
    private int itemId;
    private String itemName;
    private MeasuringUnitTypes measuringUnit;
    private double balanceQty;
    private double supplierPrice;
    private double sellingPrice;
}
