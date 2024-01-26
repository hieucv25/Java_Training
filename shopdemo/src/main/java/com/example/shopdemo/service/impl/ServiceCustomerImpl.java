package com.example.shopdemo.service.impl;

import com.example.shopdemo.model.Customer;
import com.example.shopdemo.model.dto.CustomerDTO;
import com.example.shopdemo.repository.CustomerRepository;
import com.example.shopdemo.service.ServiceCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCustomerImpl implements ServiceCustomer {


    @Autowired
    private CustomerRepository rp;

    @Override
    public Page<Customer> pagation(Pageable pageable) {
        return rp.findAll(pageable);
    }

    @Override
    public boolean save(Customer customer) {
        rp.saveAndFlush(customer);
        // if return true then the customer was added successfully
        return rp.existsByUserName(customer.getUsername());
    }

    @Override
    public void update(Customer customer) {
        rp.saveAndFlush(customer);
    }

    @Override
    public boolean deleteById(int id) {
        boolean isExists = rp.existsById(id);
        if(isExists){
            rp.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public List<Customer> getAll() {
        return rp.findAll();
    }

    @Override
    public void dowload(List<Object> list) {
        if (list instanceof CustomerDTO) {

        }
    }

    @Override
    public String validate(Customer customer) {
        if (customer.getName() == null) {
            return "Name is empty!";
        }
        if (customer.getBirth() == null) {
            return "Birth is empty!";
        }
        if (customer.getUsername() == null) {
            return "Username is empty!";
        }
        if (customer.getPasswords() == null) {
            return "Password is empty!";
        }
        for (Customer cus : rp.findAll()
        ) {
            if (cus.getUsername().equalsIgnoreCase(customer.getUsername())) {
                return "Username duplicate";
            }
        }
        return "Success";
    }
}
