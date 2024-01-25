package com.example.shopdemo.controller;

import com.example.shopdemo.model.Customer;
import com.example.shopdemo.model.dto.CustomerDTO;
import com.example.shopdemo.repository.CartRepository;
import com.example.shopdemo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*",allowCredentials = "true")
@RequestMapping("/api/admin/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository rp;
    @Autowired
    private CartRepository cartRepository;

    @GetMapping(value="/page/{number}")
    private ResponseEntity<?>paganation (@PathVariable("number")int number){
        Pageable pageable = PageRequest.of(number,50,Sort.by("createAt"));
        Page<Customer> page = rp.findAll(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping(value="/getCart")
    private ResponseEntity<?>getCart () {
        return new ResponseEntity<>(cartRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value="/findAll")
    public ResponseEntity<?> findAll (){
        List<CustomerDTO> customerDTOList = rp.findAll().stream()
                .map(CustomerDTO::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(customerDTOList,HttpStatus.OK);
    }

    @DeleteMapping(value="/delete/{id}")
    private ResponseEntity<?>deleteById (@PathVariable("id") int id){
        rp.deleteById(id);
        return new ResponseEntity<>(rp.existsById(id), HttpStatus.OK);
    }

    @PutMapping(value="/update")
    private ResponseEntity<?> update (@RequestBody Customer customer){
        rp.saveAndFlush(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    @PostMapping(value="/save")
    private ResponseEntity<?>save (@RequestBody Customer customer){
        customer.setCreateAt(LocalDateTime.now());
        return new ResponseEntity<>(rp.saveAndFlush(customer), HttpStatus.OK);
    }
}
