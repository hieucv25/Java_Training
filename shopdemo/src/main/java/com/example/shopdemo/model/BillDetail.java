package com.example.shopdemo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name="billDetail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name="quantity")
    private int quantity;
    @ManyToOne
    @JoinColumn(name="id_product")
    private Product product;
    @Column(name="createAt")
    private LocalDateTime createAt;
    @ManyToOne
    @JoinColumn(name="id_bill")
    private Bill bill;
}
