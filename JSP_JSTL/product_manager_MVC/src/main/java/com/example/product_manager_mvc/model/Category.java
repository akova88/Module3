package com.example.product_manager_mvc.model;

import java.time.LocalDate;
import java.util.Date;

public class Category {
    private int idCategory;
    private String nameCategory;
    private LocalDate deleteAt;

    public Category() {
    }

    public Category(int idCategory, String nameCategory, LocalDate deleteAt) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
        this.deleteAt = deleteAt;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public LocalDate getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(LocalDate deleteAt) {
        this.deleteAt = deleteAt;
    }
}
