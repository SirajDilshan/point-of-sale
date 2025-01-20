package com.pos.point_of._sale.service.impl;

import com.pos.point_of._sale.dto.CustomerDTO;
import com.pos.point_of._sale.dto.ItemDTO;
import com.pos.point_of._sale.dto.request.ItemSaveRequestDTO;
import com.pos.point_of._sale.entity.Customer;
import com.pos.point_of._sale.entity.Item;
import com.pos.point_of._sale.exception.EntryDuplicationException;
import com.pos.point_of._sale.repository.ItemRepo;
import com.pos.point_of._sale.service.ItemService;
import com.pos.point_of._sale.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceIMPL implements ItemService {
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ModelMapper modelMapper;

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

    @Override
    public List<ItemDTO> getAllItem() {
        List<Item> getItems = itemRepo.findAll();
        List<ItemDTO> itemDTOList = new ArrayList<>();


        List<ItemDTO> itemDTOS = modelMapper.map(getItems, new TypeToken<List<ItemDTO>>() {
        }.getType());
        return itemDTOS;
    }

    @Override
    public List<ItemDTO> getAllItemsByStateType(boolean status) {
        List<Item> getItems = itemRepo.findAllByActiveStateEquals(status);
        List<ItemDTO> itemDTOS = modelMapper.map(getItems, new TypeToken<List<ItemDTO>>() {
        }.getType());
        return itemDTOS;
    }

    @Override
    public int countALLItems() {
        int count = itemRepo.countAllByActiveStateEquals(true);
        return count;
    }
}
