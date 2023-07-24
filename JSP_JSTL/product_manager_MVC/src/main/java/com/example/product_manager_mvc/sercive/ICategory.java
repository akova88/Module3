package com.example.product_manager_mvc.sercive;

import com.example.product_manager_mvc.model.Category;

import java.util.List;

public interface ICategory {
    List<Category> getAllCategory();
    Category findById(int idCategogy);
}
