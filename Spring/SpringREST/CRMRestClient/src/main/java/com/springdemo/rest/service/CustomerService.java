package com.springdemo.rest.service;

import com.springdemo.rest.model.Customer;
import com.springdemo.rest.model.User;
import com.springdemo.rest.user.CrmUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;


public interface CustomerService {

    public List<Customer> getCustomers();

    public void saveCustomer(Customer customer);

    public Customer getCustomer(int id);

    public void deleteCustomer(int id);

    public List<String> login(User user);

    void addUser(CrmUser crmUser);
}
