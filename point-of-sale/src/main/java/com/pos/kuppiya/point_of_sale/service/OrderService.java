package com.pos.kuppiya.point_of_sale.service;

import com.pos.kuppiya.point_of_sale.dto.paginated.PaginatedResponseOrderDetailsDTO;
import com.pos.kuppiya.point_of_sale.dto.request.OrderSaveRequestDTO;
import jakarta.validation.constraints.Max;

public interface OrderService {
    String addOrder(OrderSaveRequestDTO orderSaveRequestDTO);

    PaginatedResponseOrderDetailsDTO getAllOrderDetails(boolean status, int page, @Max(50) int size);
}
