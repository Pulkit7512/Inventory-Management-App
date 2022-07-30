package com.app.inventorymanagerapp.service;

import com.app.inventorymanagerapp.entity.Product;

import java.util.List;

public interface ProductService {

    public List<Product> findAll();

    public Product findById(int id);

    public void save(Product product);

    public void deleteById(int id);
}
