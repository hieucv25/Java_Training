package com.example.shopdemo.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name="bill")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name="money_bill")
    private BigDecimal money;
    @Column(name="time_payment")
    private LocalDateTime timePayment;
    @Column(name="createAt")
    private LocalDateTime createAt;
    @ManyToOne
    @JoinColumn(name="id_cart")
    private Cart cart;
}
