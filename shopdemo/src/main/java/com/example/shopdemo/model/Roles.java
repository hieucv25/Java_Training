package com.example.shopdemo.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "roles")
@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="role_name")
    private String roleName;


}
