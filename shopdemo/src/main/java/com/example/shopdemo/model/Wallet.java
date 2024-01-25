package com.example.shopdemo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name="Wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name="money_wl")
    private BigDecimal money;
    @Column(name="createAt")
    private LocalDateTime createAt;
    @ManyToOne
    @JoinColumn(name="id_customer")
    private Customer customer;
}
