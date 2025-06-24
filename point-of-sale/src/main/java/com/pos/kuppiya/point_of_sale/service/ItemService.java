package com.pos.kuppiya.point_of_sale.service;

import com.pos.kuppiya.point_of_sale.dto.ItemDTO;
import com.pos.kuppiya.point_of_sale.dto.paginated.PaginatedResponseItemDTO;
import com.pos.kuppiya.point_of_sale.dto.request.ItemSaveRequestDTO;
import com.pos.kuppiya.point_of_sale.dto.request.ItemUpdateRequestDTO;
import jakarta.validation.constraints.Max;

import java.util.List;

public interface ItemService {
    String addItem(ItemSaveRequestDTO itemSaveRequestDTO);

    List<ItemDTO> getAllItems();

    List<ItemDTO> getAllItemsByStateType(boolean status);

    String updateItem(ItemUpdateRequestDTO itemUpdateRequestDTO);

    int deleteItem(int id);

    int getActiveCustomerCount();

    int getInactiveCustomerCount();

    PaginatedResponseItemDTO getAllItemsPaginated(int page, @Max(50) int size);


    PaginatedResponseItemDTO getAllActiveItemsPaginated(int page, @Max(50) int size, boolean activeState);
}
