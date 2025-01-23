package com.pos.point_of._sale.service;

import com.pos.point_of._sale.dto.ItemDTO;
import com.pos.point_of._sale.dto.peginated.PaginatedResponseItemDTO;
import com.pos.point_of._sale.dto.request.ItemSaveRequestDTO;

import java.util.List;

public interface ItemService {
    String addItem(ItemSaveRequestDTO itemSaveRequestDTO);


    List<ItemDTO> getAllItem();

    List<ItemDTO> getAllItemsByStateType(boolean status);


    int countALLItems();


    PaginatedResponseItemDTO getAllItemPaginated(int page, int size);
}
