package com.example.shopdemo.controller;

import com.example.shopdemo.model.Customer;
import com.example.shopdemo.model.dto.CustomerDTO;
import com.example.shopdemo.repository.CartRepository;
import com.example.shopdemo.service.impl.ServiceCustomerImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    private ServiceCustomerImpl svCustomer;
    @Autowired
    private CartRepository cartRepository;

    @GetMapping(value="/page/{number}")
    @Operation(
            description = "Customer Service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Get Paging User Successfully!",
                            content = @Content(
                                    mediaType = "Application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"Code\":200,\"Status\": \"Ok\",\"Message\":\"Get Paging User Successfully!\"}"
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
    private ResponseEntity<?>paganation (@PathVariable("number")int number){
        Pageable pageable = PageRequest.of(number,50,Sort.by("createAt"));
        Page<Customer> page = svCustomer.pagation(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping(value="/getCart")
    @Operation(
            description = "Customer Service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Get List All Cart Successfully!",
                            content = @Content(
                                    mediaType = "Application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"Code\":200,\"Status\": \"Ok\",\"Message\":\"Get List All Cart Successfully!\"}"
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
    private ResponseEntity<?>getCart () {
        return new ResponseEntity<>(cartRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value="/findAll")
    @Operation(
            description = "Customer Service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Get List All User DTO Successfully!",
                            content = @Content(
                                    mediaType = "Application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"Code\":200,\"Status\": \"Ok\",\"Message\":\"Get List All User DTO Successfully!\"}"
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
    public ResponseEntity<?> findAll (){
        List<CustomerDTO> customerDTOList = svCustomer.getAll().stream()
                .map(CustomerDTO::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(customerDTOList,HttpStatus.OK);
    }

    @DeleteMapping(value="/delete/{id}")
    @Operation(
            description = "Customer Service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Delete User Successfully!",
                            content = @Content(
                                    mediaType = "Application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"Code\":200,\"Status\": \"Ok\",\"Message\":\"Delete User Successfully!\"}"
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
    private ResponseEntity<?>deleteById (@PathVariable("id") int id){
        return new ResponseEntity<>(svCustomer.deleteById(id), HttpStatus.OK);
    }

    @PutMapping(value="/update")
    @Operation(
            description = "Customer Service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Update User Successfully!",
                            content = @Content(
                                    mediaType = "Application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"Code\":200,\"Status\": \"Ok\",\"Message\":\"Update User Successfully!\"}"
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
    private ResponseEntity<?> update (@RequestBody Customer customer){
        svCustomer.update(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping(value="/save")
    @Operation(
            description = "Customer Service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Add User Successfully!",
                            content = @Content(
                                    mediaType = "Application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"Code\":200,\"Status\": \"Ok\",\"Message\":\"Add User Successfully!\"}"
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
    private ResponseEntity<?>save (@RequestBody Customer customer){
        customer.setCreateAt(LocalDateTime.now());
        String isValidate = svCustomer.validate(customer);
        if (isValidate.equalsIgnoreCase("success")) {
            svCustomer.save(customer);
            return ResponseEntity.ok().body("{\"Code\":200,\"Status\": \"Ok\",\"Message\":\"Add User Successfully!\"}");
        } else {
            return ResponseEntity.ok().body("{\"Code\":200,\"Status\": \"Not Ok\",\"Message\":\"" + isValidate + "\"}");
        }

    }
}
