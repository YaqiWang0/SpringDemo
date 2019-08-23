package com.springdemo.controller;


import com.springdemo.entity.Customer;
import com.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RequestMapping("/customer")
public class ConsumerController {

    //need to inject teh customer dao
    @Autowired
    private CustomerService customerService;



    @GetMapping("/list")
    public String listConsumer(Model model){

        //get customers from the dao
        List<Customer> customers=customerService.getCustomers();

        //add  the customers to the model
        model.addAttribute("customers",customers);

        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Customer customer=new Customer();

        model.addAttribute("customer",customer);
        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer")Customer customer){

        customerService.saveCustomer(customer);

        return "redirect:/customer/list";
    }


    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId, Model model){
        Customer customer= customerService.getCustomer(theId);

        model.addAttribute("customer",customer);

        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int id){
        customerService.deleteCustomer(id);
        return "redirect:/customer/list";
    }

    @GetMapping("/search")
    public String searchCustomers(@RequestParam("theSearchName")String name,Model model){
        List<Customer> customers=customerService.searchCustomers(name);

        model.addAttribute("customers",customers);
        return "list-customers";
    }


}
