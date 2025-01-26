package com.pos.point_of._sale.service;

import com.pos.point_of._sale.dto.request.RequestOrderSaveDTO;

public interface OrderService {
    String addOrder(RequestOrderSaveDTO requestOrderSaveDTO);
}
