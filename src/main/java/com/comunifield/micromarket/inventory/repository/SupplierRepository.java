package com.comunifield.micromarket.inventory.repository;

import com.comunifield.micromarket.inventory.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    boolean existsByNit(String nit);
}