package com.comunifield.micromarket.inventory.repository;

import com.comunifield.micromarket.inventory.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}