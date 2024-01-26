package com.example.shopdemo.controller;

import com.example.shopdemo.model.Product;
import com.example.shopdemo.repository.ProductRepository;
import com.example.shopdemo.service.impl.ServiceProductImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/product")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*",allowCredentials = "true")
public class ProductController {

    @Autowired
    private ProductRepository rp;

    @Autowired
    private ServiceProductImpl sv;

    @GetMapping("/findAll")
    @Operation(
            description = "Product Service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Find All Product Successfully!",
                            content = @Content(
                                    mediaType = "Application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"Code\":200,\"Status\": \"Ok\",\"Message\":\"Find All Product Successfully!\"}"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            ref = "badRequestAPI"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            ref = "internalServerErrorAPI"
                    ),
            }
    )
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(sv.findALl(), HttpStatus.OK);
    }

    @PostMapping("/save")
    @Operation(
            description = "Product Service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Add Product Successfully!",
                            content = @Content(
                                    mediaType = "Application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"Code\":200,\"Status\": \"Ok\",\"Message\":\"Add Product Successfully!\"}"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            ref = "badRequestAPI"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            ref = "internalServerErrorAPI"
                    ),
            }
    )
    private ResponseEntity<?> save(@RequestBody Product product){
        product.setCreateAt(LocalDateTime.now());
        sv.save(product);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(
            description = "Product Service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Update Product Successfully!",
                            content = @Content(
                                    mediaType = "Application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"Code\":200,\"Status\": \"Ok\",\"Message\":\"Update Product Successfully!\"}"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            ref = "badRequestAPI"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            ref = "internalServerErrorAPI"
                    ),
            }
    )
    private ResponseEntity<?> update(@RequestBody Product product){
        product.setCreateAt(LocalDateTime.now());
        sv.update(product);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    @Operation(
            description = "Product Service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Delete Product Successfully!",
                            content = @Content(
                                    mediaType = "Application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"Code\":200,\"Status\": \"Ok\",\"Message\":\"Delete Product Successfully!\"}"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            ref = "badRequestAPI"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            ref = "internalServerErrorAPI"
                    ),
            }
    )
    private ResponseEntity<?> delete(@PathVariable("id") String id){
        // if id exists then product deletion is performed
        boolean isExists = sv.existById(UUID.fromString(id));
        if(isExists) {
            sv.delete(UUID.fromString(id));
            // if response data return true then delete product successfully
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            // if response data return false then delete product successfully
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
    }
}
