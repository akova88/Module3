package com.example.customers_manager_mvc.model;

import java.util.Date;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String address;
    private Date createAt;

    private int idType;
    private CustomerType customerType;

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public Customer() {
    }

    public Customer(int id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Customer(int id, String name, String email, String address, Date createAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.createAt = createAt;
    }

    public Customer(int id, String name, String email, String address, Date createAt, int idType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.createAt = createAt;
        this.idType = idType;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
