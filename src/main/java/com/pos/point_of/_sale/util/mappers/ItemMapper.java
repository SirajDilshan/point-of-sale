package com.pos.point_of._sale.util.mappers;

import com.pos.point_of._sale.dto.ItemDTO;
import com.pos.point_of._sale.dto.request.ItemSaveRequestDTO;
import com.pos.point_of._sale.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    // Map ItemSaveRequestDTO → Item (ignore auto-generated fields)
    @Mapping(target = "itemId", ignore = true)  // DB auto-generates this
    @Mapping(target = "activeState", constant = "true") // New items are active by default
    @Mapping(target = "orderDetails", ignore = true)    // Ignore JPA relationship
    Item RequestDtoToEntity(ItemSaveRequestDTO itemSaveRequestDTO);

    // Map Item → ItemDTO (auto-mapped if field names match)
    ItemDTO EntityToDto(Item item);

    // Convert Page<Item> → List<ItemDTO>
    default List<ItemDTO> pageToList(Page<Item> page) {
        return page.getContent()
                .stream()
                .map(this::EntityToDto)
                .toList();
    }
}