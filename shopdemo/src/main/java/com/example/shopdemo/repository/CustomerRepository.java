package com.example.shopdemo.repository;

import com.example.shopdemo.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Override
    <S extends Customer> S saveAndFlush(S entity);

    @Override
    List<Customer> findAll();

    @Override
    <S extends Customer> S save(S entity);

    @Override
    Optional<Customer> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    Page<Customer> findAll(Pageable pageable);

    @Query("select kh from Customer kh where kh.userName = :user_name")
    Customer getByUserName(@Param("user_name") String user_name);

}
