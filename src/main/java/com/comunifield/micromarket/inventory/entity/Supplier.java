package com.comunifield.micromarket.inventory.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nit", nullable = false, unique = true)
    private String nit;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;
}