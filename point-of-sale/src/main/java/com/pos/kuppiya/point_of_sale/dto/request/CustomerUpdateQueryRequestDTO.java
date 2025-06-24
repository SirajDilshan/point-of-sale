package com.pos.kuppiya.point_of_sale.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerUpdateQueryRequestDTO {
    private String customerName;
    private String nic;
}
