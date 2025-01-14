package com.pos.point_of._sale.service;

import com.pos.point_of._sale.dto.request.ItemSaveRequestDTO;

public interface ItemService {
    String addItem(ItemSaveRequestDTO itemSaveRequestDTO);
}
