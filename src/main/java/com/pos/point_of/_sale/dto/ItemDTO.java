package com.pos.point_of._sale.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pos.point_of._sale.entity.enums.MeasuringUnitType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO {

    private int itemId;
    private String itemName;
    private MeasuringUnitType measuringUnitType;
    private double balanceQty;
    private double supplierPrice;
    private double sellingPrice;
}
