package com.example.shopdemo.repository;

import com.example.shopdemo.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Override
    List<Cart> findAll();
}
