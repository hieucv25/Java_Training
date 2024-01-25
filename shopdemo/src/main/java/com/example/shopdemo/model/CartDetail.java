package com.example.shopdemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="cartDetail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="quantity")
    private int quantity;

    @Column(name="createAt")
    private LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(name="id_cart")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name="id_product")
    private Product product;
}
