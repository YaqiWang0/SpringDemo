package com.springdemo.rest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class DemoRestController {

    //add code for the "/hello" endpoint

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World";
    }
}
