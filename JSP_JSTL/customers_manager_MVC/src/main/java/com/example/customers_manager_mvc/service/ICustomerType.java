package com.example.customers_manager_mvc.service;

import com.example.customers_manager_mvc.model.CustomerType;

import java.util.List;

public interface ICustomerType {
    List<CustomerType> getAllCustomerTypes();
    CustomerType findById(long id);
}
