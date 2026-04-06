package com.comunifield.micromarket.inventory.entity;

import com.comunifield.micromarket.inventory.entity.Employee;
import com.comunifield.micromarket.inventory.entity.SaleDetail;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    private Double subtotal;
    private Double iva;
    private Double total;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleDetail> details;
}