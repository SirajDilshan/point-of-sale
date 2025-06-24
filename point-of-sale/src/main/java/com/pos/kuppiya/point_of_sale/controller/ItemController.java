package com.pos.kuppiya.point_of_sale.controller;


import com.pos.kuppiya.point_of_sale.dto.ItemDTO;
import com.pos.kuppiya.point_of_sale.dto.paginated.PaginatedResponseItemDTO;
import com.pos.kuppiya.point_of_sale.dto.request.ItemSaveRequestDTO;
import com.pos.kuppiya.point_of_sale.dto.request.ItemUpdateRequestDTO;
import com.pos.kuppiya.point_of_sale.service.ItemService;
import com.pos.kuppiya.point_of_sale.util.StandardResponse;
import jakarta.validation.constraints.Max;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping(path = "/save")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO) {
        String id = itemService.addItem(itemSaveRequestDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,id+" item saved success",id),
                HttpStatus.CREATED
        );
    }

    @GetMapping(path = "/get-all-items")
    public ResponseEntity<StandardResponse> getAllCustomers() {
        List<ItemDTO> allItems = itemService.getAllItems();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"success",allItems),
                HttpStatus.OK
        );
    }

    @GetMapping(path = {"/get-all-items-state"},params = {"state"})
    public ResponseEntity<StandardResponse> getAllItemsBystate(@RequestParam (value = "state")String state) {
        if(state.equalsIgnoreCase("ACTIVE")||state.equalsIgnoreCase("INACTIVE")) {
            boolean status = state.equalsIgnoreCase("ACTIVE")?true:false;
            List<ItemDTO> allItems = itemService.getAllItemsByStateType(status);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200,"success",allItems),
                    HttpStatus.OK
            );
        }else if (state.equalsIgnoreCase("all"))
        {
            List<ItemDTO> allItems = itemService.getAllItems();
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200,"success",allItems),
                    HttpStatus.OK
            );
        }
        else {
            return null;
        }
    }

    @PutMapping(path = "/updateById")
    public  ResponseEntity<StandardResponse> updateItemById(@RequestBody ItemUpdateRequestDTO itemUpdateRequestDTO) {
        String id = itemService.updateItem(itemUpdateRequestDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,id+" item updated success",id),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping(path = "/delete-item/{id}")
    public  ResponseEntity<StandardResponse> deleteItem(@PathVariable(value = "id")int id) {
        int deletedItem = itemService.deleteItem(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Item deletion success ",id),
                HttpStatus.CREATED
        );
    }

    @GetMapping(path = {"/get-all-items-count-by-state"})
    public ResponseEntity<StandardResponse> getActiveInactiveAllItemsCount() {
        int active = itemService.getActiveCustomerCount();
        int inactive = itemService.getInactiveCustomerCount();
        int all = active + inactive ;

        Map<String, Integer> itemCounts = new HashMap<>();
        itemCounts.put("active" , active);
        itemCounts.put("inactive" , inactive);
        itemCounts.put("all" , all);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Item counts",itemCounts),
                HttpStatus.CREATED
        );
    }

    @GetMapping(
            path = {"get-all-paginated-items"},
            params = {"page","size"}
    )
    public ResponseEntity<StandardResponse> getAllPaginatedItems(@RequestParam(value = "page") int page,@RequestParam(value = "size") @Max(50) int size) {
        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getAllItemsPaginated(page,size);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Item counts",paginatedResponseItemDTO),
                HttpStatus.CREATED
        );
    }

    @GetMapping(
            path = {"get-all-active-paginated-items"},
            params = {"page","size","activeState"}
    )
    public ResponseEntity<StandardResponse> getAllActivePaginatedItems(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(50) int size,
            @RequestParam(value = "activeState") boolean activeState
            ) {
        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getAllActiveItemsPaginated(page,size,activeState);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Item counts",paginatedResponseItemDTO),
                HttpStatus.CREATED
        );
    }
}
