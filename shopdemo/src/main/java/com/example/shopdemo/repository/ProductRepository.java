package com.example.shopdemo.repository;

import com.example.shopdemo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Override
    <S extends Product> S saveAndFlush(S entity);

    @Override
    List<Product> findAll();

    @Override
    Optional<Product> findById(UUID uuid);

    @Override
    boolean existsById(UUID uuid);

    @Override
    void deleteById(UUID uuid);

    @Override
    List<Product> findAll(Sort sort);

    @Override
    Page<Product> findAll(Pageable pageable);
}
