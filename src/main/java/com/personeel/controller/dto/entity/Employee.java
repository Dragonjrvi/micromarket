package com.comunifield.micromarket.personnel.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String cedula;

    @Column(nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Cargo cargo;

    @Column(nullable = false)
    private LocalDate fechaIngreso;

    @Column(nullable = false)
    private BigDecimal salario;

    public enum Cargo {
        ADMINISTRADOR, CAJERO, AUXILIAR
    }
}