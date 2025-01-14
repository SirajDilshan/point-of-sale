package com.pos.point_of._sale.util.mappers;

import com.pos.point_of._sale.dto.request.ItemSaveRequestDTO;
import com.pos.point_of._sale.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {


    @Mapping(target = "itemId", ignore = true)  // Since it's auto-generated
    @Mapping(target = "activeState", ignore = true)
    Item RequestDtoToEntity(ItemSaveRequestDTO itemSaveRequestDTO);
}
