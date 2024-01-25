package com.example.shopdemo.service;

import com.example.shopdemo.model.Customer;
import com.example.shopdemo.model.dto.CustomerDTO;

import java.util.List;

public interface ServiceCustomer {

    List<Customer> getAll();

    void dowload(List<Object> list);
}
