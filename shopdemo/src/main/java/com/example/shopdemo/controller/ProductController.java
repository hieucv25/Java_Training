package com.example.shopdemo.controller;

import com.example.shopdemo.model.Product;
import com.example.shopdemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/product")
@CrossOrigin(origins = "localhost:4200")
public class ProductController {

    @Autowired
    private ProductRepository rp;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(rp.findAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    private ResponseEntity<?> save(@RequestBody Product product){
        product.setCreateAt(LocalDateTime.now());
        return new ResponseEntity<>(rp.saveAndFlush(product), HttpStatus.OK);
    }

    @PutMapping("/update")
    private ResponseEntity<?> update(@RequestBody Product product){
        product.setCreateAt(LocalDateTime.now());
        return new ResponseEntity<>(rp.saveAndFlush(product), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") String id){
        rp.deleteById(UUID.fromString(id));
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
