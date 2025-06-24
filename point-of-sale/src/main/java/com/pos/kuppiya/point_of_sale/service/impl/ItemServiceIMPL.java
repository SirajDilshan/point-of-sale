package com.pos.kuppiya.point_of_sale.service.impl;

import com.pos.kuppiya.point_of_sale.dto.ItemDTO;
import com.pos.kuppiya.point_of_sale.dto.paginated.PaginatedResponseItemDTO;
import com.pos.kuppiya.point_of_sale.dto.request.ItemSaveRequestDTO;
import com.pos.kuppiya.point_of_sale.dto.request.ItemUpdateRequestDTO;
import com.pos.kuppiya.point_of_sale.entity.Item;
import com.pos.kuppiya.point_of_sale.exception.EntryDuplicateException;
import com.pos.kuppiya.point_of_sale.repo.ItemRepo;
import com.pos.kuppiya.point_of_sale.service.ItemService;
import com.pos.kuppiya.point_of_sale.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ItemServiceIMPL implements ItemService {
    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public String addItem(ItemSaveRequestDTO itemSaveRequestDTO) {
        Item item = itemMapper.RequestDTOtoEntity(itemSaveRequestDTO);
        item.setActiveState(true);
        if(!itemRepo.existsById(item.getItemId())){
            return itemRepo.save(item).getItemName();
        }else {
            throw new EntryDuplicateException("Entry already exists");
        }
    }

    @Override
    public List<ItemDTO> getAllItems() {
        List<Item> getItems = StreamSupport
                .stream(itemRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
        List<ItemDTO> itemDTOS = modelMapper.map(getItems, new TypeToken<List<ItemDTO>>() {}.getType());
        return itemDTOS;
    }

    @Override
    public List<ItemDTO> getAllItemsByStateType(boolean status) {
        List<Item> getItems = StreamSupport
                .stream(itemRepo.findAllByActiveStateEquals(status).spliterator(), false)
                .collect(Collectors.toList());
        List<ItemDTO> itemDTOS = modelMapper.map(getItems, new TypeToken<List<ItemDTO>>() {}.getType());
        return itemDTOS;
    }

    @Override
    public String updateItem(ItemUpdateRequestDTO itemUpdateRequestDTO) {
        if (itemRepo.existsById(itemUpdateRequestDTO.getItemId())) {
            Item item = itemRepo.getReferenceById(itemUpdateRequestDTO.getItemId());

            item.setItemName(itemUpdateRequestDTO.getItemName());
            item.setMeasuringUnit(itemUpdateRequestDTO.getMeasuringUnit());
            item.setBalanceQty(itemUpdateRequestDTO.getBalanceQty());
            item.setSupplierPrice(itemUpdateRequestDTO.getSupplierPrice());
            item.setSellingPrice(itemUpdateRequestDTO.getSellingPrice());

            return itemRepo.save(item).getItemName();
        } else {
            throw new EntryDuplicateException("Not in database");
        }
    }

    @Override
    public int deleteItem(int id) {
        if (itemRepo.existsById(id)) {
            itemRepo.deleteById(id);
        } else {
            throw new NoSuchElementException("Not found customer for this id");
        }
        return id;
    }

    @Override
    public int getActiveCustomerCount() {
        return itemRepo.countByActiveState(true);
    }

    @Override
    public int getInactiveCustomerCount() {
        return itemRepo.countByActiveState(false);
    }

    @Override
    public PaginatedResponseItemDTO getAllItemsPaginated(int page, int size) {
        Page<Item> getAllItemsByPaginated = itemRepo.findAll(PageRequest.of(page, size));
        return new PaginatedResponseItemDTO(
                itemMapper.pageToList(getAllItemsByPaginated),
                itemRepo.count()
        );
    }

    @Override
    public PaginatedResponseItemDTO getAllActiveItemsPaginated(int page, int size, boolean activeState) {
        Page<Item> getAllItemsByPaginated = itemRepo.findAllByActiveStateEquals(activeState,PageRequest.of(page, size));
        return new PaginatedResponseItemDTO(
                itemMapper.pageToList(getAllItemsByPaginated),
                itemRepo.countByActiveState(activeState)
        );
    }


}
