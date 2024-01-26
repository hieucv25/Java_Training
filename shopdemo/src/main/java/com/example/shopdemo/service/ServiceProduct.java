package com.example.shopdemo.service;

import com.example.shopdemo.model.Product;

import java.util.List;
import java.util.UUID;

public interface ServiceProduct {
    List<Product> findALl();

    void save(Product product);

    void update(Product product);

    void delete(UUID id);

    boolean existById(UUID id);
}
