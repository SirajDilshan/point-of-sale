package com.pos.point_of._sale.controller;

import com.pos.point_of._sale.dto.request.ItemSaveRequestDTO;
import com.pos.point_of._sale.dto.request.RequestOrderSaveDTO;
import com.pos.point_of._sale.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order")
@CrossOrigin
public class OrderController {
    @PostMapping(path = "/save")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody RequestOrderSaveDTO requestOrderSaveDTO) {
        System.out.println(requestOrderSaveDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,2 + " item successfully saved" , 2),
                HttpStatus.CREATED
        );
    }
}
