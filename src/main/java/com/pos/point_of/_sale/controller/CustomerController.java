package com.pos.point_of._sale.controller;

import com.pos.point_of._sale.dto.CustomerDTO;
import com.pos.point_of._sale.dto.request.CustomerSaveRequestDTO;
import com.pos.point_of._sale.dto.request.CustomerUpdateRequestDTO;
import com.pos.point_of._sale.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerService customerService;
//    @GetMapping(path = "/get-1")
//    public String getMyText(){
//        String myText="this is our first spingboot project";
//        System.out.println(myText);
//        return myText;
//    }
//
//    @GetMapping(path = "/get-2")
//    public void getMyText1(){
//        String myText="this is our second spingboot project";
//        System.out.println(myText);
//    }

    @PostMapping(path = "/save")
    public String saveCustomer(@RequestBody CustomerSaveRequestDTO customerSaveRequestDTO){
        String id =customerService.addCustomer(customerSaveRequestDTO);
        return id;
    }

    @PutMapping(path = "/update")
    public String updateCustomer(@RequestBody CustomerUpdateRequestDTO customerUpdateRequestDTO){
        String updated = customerService.updateCustomer(customerUpdateRequestDTO);
        return updated;
    }
}
