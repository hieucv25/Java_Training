package com.example.shopdemo.service.impl;

import com.example.shopdemo.model.Customer;
import com.example.shopdemo.model.dto.CustomerDTO;
import com.example.shopdemo.repository.CustomerRepository;
import com.example.shopdemo.service.ServiceCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCustomerImpl implements ServiceCustomer {
    @Autowired
    private CustomerRepository rp;

    @Override
    public List<Customer> getAll() {
        return rp.findAll();
    }

    @Override
    public void dowload(List<Object> list) {
        if(list instanceof CustomerDTO){

        }
    }
}
