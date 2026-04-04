package com.comunifield.micromarket.inventory.repository;

import com.comunifield.micromarket.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByBarcode(String barcode);
}