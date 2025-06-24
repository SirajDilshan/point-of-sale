package com.pos.kuppiya.point_of_sale.util.mappers;

import com.pos.kuppiya.point_of_sale.dto.ItemDTO;
import com.pos.kuppiya.point_of_sale.dto.paginated.PaginatedResponseItemDTO;
import com.pos.kuppiya.point_of_sale.dto.request.ItemSaveRequestDTO;
import com.pos.kuppiya.point_of_sale.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item RequestDTOtoEntity(ItemSaveRequestDTO itemSaveRequestDTO);
    List<ItemDTO> pageToList(Page<Item> page);
}
