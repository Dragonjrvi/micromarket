package com.comunifield.micromarket.inventory.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter @Setter
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="document",nullable = false, unique = true)
    private String cedula;

    @Column(name="name",nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name="position",nullable = false)
    private Cargo cargo;

    @Column(name="hire_date",nullable = false)
    private LocalDate fechaIngreso;

    @Column(name="salary",nullable = false)
    private BigDecimal salario;

    public enum Cargo {
        ADMINISTRADOR, CAJERO, AUXILIAR
    }
}
