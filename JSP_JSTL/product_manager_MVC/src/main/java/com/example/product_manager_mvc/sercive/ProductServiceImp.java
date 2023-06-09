package com.example.product_manager_mvc.sercive;

import com.example.product_manager_mvc.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImp implements IProductService{
private static Map<Integer, Product> products;
static {
    products = new HashMap<>();
    products.put(1, new Product(1, "Iphone1", 1000, "Phone", "Apple"));
    products.put(2, new Product(2, "Iphone2", 1200, "Phone", "Apple"));
    products.put(3, new Product(3, "Iphone3", 1300, "Phone", "Apple"));
    products.put(4, new Product(4, "Iphone4", 1400, "Phone", "Apple"));
    products.put(5, new Product(5, "Iphone5", 1500, "Phone", "Apple"));
    products.put(6, new Product(6, "Iphone6", 1600, "Phone", "Apple"));
}
    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void update(int id, Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }
}
