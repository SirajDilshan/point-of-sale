package com.pos.point_of._sale.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/test")
@CrossOrigin
public class TestController {

    @GetMapping(path = "/get-1")
    public String getMyText(){
        String myText="this is our first spingboot project";
        System.out.println(myText);
        return myText;
    }

    @GetMapping(path = "/get-2")
    public void getMyText1(){
        String myText="this is our second spingboot project";
        System.out.println(myText);
    }

}
