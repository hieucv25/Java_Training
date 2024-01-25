package com.example.shopdemo.service.impl;

import com.example.shopdemo.model.Product;
import com.example.shopdemo.repository.ProductRepository;
import com.example.shopdemo.service.ServiceProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ServiceProductImpl implements ServiceProduct {

    @Autowired
    private ProductRepository rp;
    @Override
    public List<Product> findALl() {
        return rp.findAll();
    }

    @Override
    public void save(Product product) {
        rp.saveAndFlush(product);
    }

    @Override
    public void update(Product product) {
        rp.saveAndFlush(product);
    }

    @Override
    public void delete(UUID id) {
        rp.deleteById(id);
    }

    @Override
    public boolean existById(UUID id) {
        return rp.existsById(id);
    }
}
