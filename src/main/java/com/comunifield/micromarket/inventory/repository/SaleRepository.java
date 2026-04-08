package com.comunifield.micromarket.inventory.repository;

import com.comunifield.micromarket.inventory.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}