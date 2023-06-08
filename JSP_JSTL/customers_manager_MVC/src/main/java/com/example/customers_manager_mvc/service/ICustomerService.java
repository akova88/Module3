package com.example.customers_manager_mvc.service;

import com.example.customers_manager_mvc.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();

    void save(Customer customer);

    Customer findById(int id);
    void update(int id, Customer customer);
    void remove(int id);
}
