package com.example.product_manager_mvc.model;

public class Product {
//    id, tên sản phẩm, giá sản phẩm, mô tả của sản phẩm, nhà sản xuất.
    private int id;
    private String name;
    private float price;
    private String description;
    private String company;
    private int idCategory;
    private Category category;

    public Product() {
    }

    public Product(int id, String name, float price, String description, String company) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.company = company;
    }

    public Product(int id, String name, float price, String description, String company, int idCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.company = company;
        this.idCategory = idCategory;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
