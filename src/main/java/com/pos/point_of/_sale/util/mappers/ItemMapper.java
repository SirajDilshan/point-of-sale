package com.pos.point_of._sale.util.mappers;

import com.pos.point_of._sale.dto.ItemDTO;
import com.pos.point_of._sale.dto.peginated.PaginatedResponseItemDTO;
import com.pos.point_of._sale.dto.request.ItemSaveRequestDTO;
import com.pos.point_of._sale.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {


    @Mapping(target = "itemId", ignore = true)  // Since it's auto-generated
    @Mapping(target = "activeState", ignore = true)
    Item RequestDtoToEntity(ItemSaveRequestDTO itemSaveRequestDTO);

    List<ItemDTO> pageToList(Page<Item>page);


}
