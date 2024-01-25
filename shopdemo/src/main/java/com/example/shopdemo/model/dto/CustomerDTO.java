package com.example.shopdemo.model.dto;

import com.example.shopdemo.model.Customer;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private int id;
    private String name;
    private LocalDate birth;
    private LocalDateTime createAt;
    private String userName;
    private String passwords;

    public static CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setBirth(customer.getBirth());
        customerDTO.setCreateAt(customer.getCreateAt());
        customerDTO.setUserName(customer.getUsername());
        customerDTO.setPasswords(customer.getPasswords());
        return customerDTO;
    }
}
