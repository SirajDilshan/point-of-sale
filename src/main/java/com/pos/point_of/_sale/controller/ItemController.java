package com.pos.point_of._sale.controller;

import com.pos.point_of._sale.dto.ItemDTO;
import com.pos.point_of._sale.dto.peginated.PaginatedResponseItemDTO;
import com.pos.point_of._sale.dto.request.ItemSaveRequestDTO;
import com.pos.point_of._sale.service.ItemService;
import com.pos.point_of._sale.util.StandardResponse;
import jakarta.validation.constraints.Max;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                new StandardResponse(201,id + " item successfully saved" , id),
                HttpStatus.CREATED
        );
    }

    @GetMapping(path = "/get-all-items")
    public ResponseEntity<StandardResponse>  getAllItems(){
        List<ItemDTO> allItems = itemService.getAllItem();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,  " success" , allItems),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-all-items-by-state",
    params = {"state"})
    public ResponseEntity<StandardResponse>  getAllItems(@RequestParam(value = "state") String state){
       if(state.equalsIgnoreCase("active") | state.equalsIgnoreCase("inactive")){
//           boolean status = false;
//           if(state.equalsIgnoreCase("active")){
//               status = true;
//           }
           boolean status = state.equalsIgnoreCase("active")?true:false;
           List<ItemDTO> allItems = itemService.getAllItemsByStateType(status);
           return new ResponseEntity<StandardResponse>(
                   new StandardResponse(200,  " success" , allItems),
                   HttpStatus.OK
           );
       }else {
           List<ItemDTO> allItems = itemService.getAllItem();
           return new ResponseEntity<StandardResponse>(
                   new StandardResponse(200,  " success" , allItems),
                   HttpStatus.OK
           );

       }



    }

@GetMapping(path = "count-all-item")
    public ResponseEntity<StandardResponse>getAllItemCounts(){
int itemCount = itemService.countALLItems();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,  " success" , itemCount),
                HttpStatus.OK
        );
    }

@GetMapping(
        path = "get-all-items-paginated",
        params = {"page","size"}
)
public ResponseEntity<StandardResponse>getAllItemsPaginated(
        @RequestParam(value = "page") int page,
        @RequestParam(value = "size") @Max(50) int size
){
    PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getAllItemPaginated(page,size);
    return new ResponseEntity<StandardResponse>(
            new StandardResponse(200,  " success" , paginatedResponseItemDTO),
            HttpStatus.OK
    );
}
}
