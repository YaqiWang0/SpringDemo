package com.springdemo.rest;


import com.springdemo.entity.Customer;
import com.springdemo.service.CustomerService;
import com.springdemo.user.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    //autowire the CustomerService
    @Autowired
    private CustomerService customerService;


    @Autowired
    private UserDetailsManager userDetailsManager;

    private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    //add mapping for GET/customers
    @GetMapping("/customers")
    public List<Customer> getCustomers(){

        return customerService.getCustomers();
    }

    @GetMapping("/customers/{customerID}")
    public Customer getCustomer(@PathVariable int customerID){

        Customer customer = customerService.getCustomer(customerID);


        if(customer==null){
            throw new CustomerNotFoundException("Customer id not found - "+customerID);
        }
        return customer;
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer){
        //in case the pass an id in JSON ... set id to 0
        //this is force a save of new item
        customer.setId(0);
        customerService.saveCustomer(customer);

        return customer;
    }

    @GetMapping("/login")
    public List<String> login(){
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<String> res=new ArrayList<>();
        for(SimpleGrantedAuthority s:authorities){
            res.add(s.getAuthority());
        }
        return res;
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer){

        customerService.saveCustomer(customer);

        return customer;
    }

    @DeleteMapping("/customers/{customerID}")
    public String deleteCustomer(@PathVariable int customerID){

        Customer customer=customerService.getCustomer(customerID);
        if(customer== null){
            throw  new CustomerNotFoundException("Customer id not found - "+customerID);
        }

        customerService.deleteCustomer(customerID);

        return "Delete customer id - "+customerID;

    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody CrmUser crmUser){

        String encodedPassword=passwordEncoder.encode(crmUser.getPassword());

        encodedPassword="{bcrypt}"+encodedPassword;

        List<GrantedAuthority> authorities= AuthorityUtils.createAuthorityList();
        authorities.add(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));

        // if the user selected role other than employee,
        // then add that one too (multiple roles)
        String formRole = crmUser.getFormRole();
        if (!formRole.equals("ROLE_EMPLOYEE")) {
            authorities.add(new SimpleGrantedAuthority(formRole));
        }

        User user=new User(crmUser.getUserName(),encodedPassword,authorities);

        userDetailsManager.createUser(user);

        return "success";

    }

}
