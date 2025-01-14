package com.pos.point_of._sale.service.impl;

import com.pos.point_of._sale.dto.request.ItemSaveRequestDTO;
import com.pos.point_of._sale.entity.Item;
import com.pos.point_of._sale.exception.EntryDuplicationException;
import com.pos.point_of._sale.repository.ItemRepo;
import com.pos.point_of._sale.service.ItemService;
import com.pos.point_of._sale.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceIMPL implements ItemService {
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public String addItem(ItemSaveRequestDTO itemSaveRequestDTO) {
        Item item = itemMapper.RequestDtoToEntity(itemSaveRequestDTO);

        item.setActiveState(true);
        if(!itemRepo.existsById(item.getItemId())){

            Item savedItem = itemRepo.save(item);
            return savedItem.getItemName();
//            return itemRepo.save(item).getItemName();
        }else {
            throw new EntryDuplicationException("Already in database");
        }

    }
}
