package com.springdemo.rest.controller;

import com.springdemo.rest.model.Customer;
import com.springdemo.rest.model.User;
import com.springdemo.rest.service.CustomerService;
import com.springdemo.rest.user.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    User user;
    // need to inject our customer service
    @Autowired
    private CustomerService customerService;

    private Map<String,String> roles;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @PostConstruct
    protected void loadRoles(){

        roles=new LinkedHashMap<>();
        roles.put("ROLE_EMPLOYEE","Employee");
        roles.put("ROLE_MANAGER","Manager");
        roles.put("ROLE_ADMIN","Admin");
    }

    @GetMapping("/list")
    public String listCustomers(Model theModel) {

        if(user.getUsername()==null) {
            theModel.addAttribute("user",user);
            return "fancy-login";
        }
        // get customers from the service
        List<Customer> theCustomers = customerService.getCustomers();

        // add the customers to the model
        theModel.addAttribute("customers", theCustomers);

        return "list-customers";
    }

    @PostMapping("login")
    public String login(Model theModel,@ModelAttribute("user") User user){
        List<String> authorities=customerService.login(user);
        theModel.addAttribute("authorities",authorities);
        List<Customer> theCustomers = customerService.getCustomers();

        this.user.setUsername(user.getUsername());
        this.user.setPassword(user.getPassword());
        // add the customers to the model
        theModel.addAttribute("customers", theCustomers);
        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Customer theCustomer = new Customer();

        theModel.addAttribute("customer", theCustomer);

        return "customer-form";
    }

    @GetMapping("/showRegistrationForm")
    public  String showMyLoginPage(Model model){
        model.addAttribute("crmUser",new CrmUser());

        model.addAttribute("roles",roles);
        return "registration-form";
    }



    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(@Valid @ModelAttribute("crmUser") CrmUser crmUser,
                                          BindingResult bindingResult,
                                          Model model){
        customerService.addUser(crmUser);
        model.addAttribute("user",user);
        return "fancy-login";

}
    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {

        // save the customer using our service
        customerService.saveCustomer(theCustomer);

        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId,
                                    Model theModel) {

        // get the customer from our service
        Customer theCustomer = customerService.getCustomer(theId);

        // set customer as a model attribute to pre-populate the form
        theModel.addAttribute("customer", theCustomer);

        // send over to our form
        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int theId) {

        // delete the customer
        customerService.deleteCustomer(theId);

        return "redirect:/customer/list";
    }
}