package com.springdemo.rest.controller;

import com.springdemo.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    User user;
    @RequestMapping("/")
    public String home(){


        return "redirect:/customer/list";
    }
}
