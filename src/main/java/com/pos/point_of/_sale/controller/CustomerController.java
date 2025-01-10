package com.pos.point_of._sale.controller;

import com.pos.point_of._sale.dto.CustomerDTO;
import com.pos.point_of._sale.dto.request.CustomerSaveRequestDTO;
import com.pos.point_of._sale.dto.request.CustomerUpdateQueryRequestDTO;
import com.pos.point_of._sale.dto.request.CustomerUpdateRequestDTO;
import com.pos.point_of._sale.dto.response.ResponseActiveCustomerDTO;
import com.pos.point_of._sale.exception.NotFoundException;
import com.pos.point_of._sale.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return updated ;
    }

    @GetMapping(
            path = "/get-by-id",
            params = "id"
                )
    public CustomerDTO getCustomerById(@RequestParam(value = "id") int id){
        CustomerDTO customerDTO=customerService.getCustomerById(id);
        return customerDTO;
    }

    @GetMapping(path = "/get-all-customers")
    public List<CustomerDTO> getAllCustomers(){
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
        return allCustomers;
    }

    @DeleteMapping(path = {"/delete-customer/{id}"})
    public String deleteCustomer(@PathVariable(value = "id") int id) throws NotFoundException {
        boolean deletedCustomer = customerService.deleteCustomer(id);
        return "delete";
    }

    @GetMapping(
            path = {"/get-by-name"},
            params = {"name"}
    )
    public List<CustomerDTO> getCustomerByName(@RequestParam(value = "name")String customerName) throws NotFoundException {
        List<CustomerDTO> getCustomer = customerService.getByName(customerName);
        return getCustomer;

    }

    @GetMapping(
            path = {"get-by-active-state"}
    )

    public List<CustomerDTO> getCustomerByActiveState() throws NotFoundException{
        List<CustomerDTO> getCustomer = customerService.getAllCustomersByActiveState();
        return getCustomer;
    }


    @GetMapping(
            path = {"get-by-active-state-only-name"}
    )

    public List<ResponseActiveCustomerDTO> getCustomerByActiveStateOnlyName() throws NotFoundException{
        List<ResponseActiveCustomerDTO> getCustomer = customerService.getAllCustomersByActiveStateOnlyName();
        return getCustomer;
    }

    @PutMapping(path = "/update-query/{id}")
    public String updateCustomerByQuery(@RequestBody CustomerUpdateQueryRequestDTO customerUpdateQueryRequestDTO,
                                        @PathVariable(value = "id") int id){
        String updated = customerService.updateCustomerByQuery(customerUpdateQueryRequestDTO,id);
        return updated ;
    }

}
