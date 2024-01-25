package com.example.shopdemo.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name="name")
    private String name;
    @Column(name="price")
    private BigDecimal price;
    @Column(name="quantity")
    private int quantity;
    @Column(name="createAt")
    private LocalDateTime createAt;

    public Product(String name, BigDecimal price, int quantity, LocalDateTime createAt) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.createAt = createAt;
    }
}
