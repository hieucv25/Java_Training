package com.example.shopdemo.service;

import com.example.shopdemo.model.Customer;
import com.example.shopdemo.model.dto.CustomerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ServiceCustomer {

    List<Customer> getAll();

    Page<Customer> pagation(Pageable pageable);

    void dowload(List<Object> list);

    String validate(Customer customer);

    boolean save (Customer customer);

    void update(Customer customer);

    boolean deleteById(int id);
}
