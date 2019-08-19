package com.springdemo.dao;

import com.springdemo.entity.Customer;

import java.util.List;

public interface CustomerDAO {


    public List<Customer> getCustomers();


    void saveCustomer(Customer customer);

    Customer getCustomer(int theId);

    void deleteCustomer(int id);

    List<Customer> searchCustomers(String name);
}
