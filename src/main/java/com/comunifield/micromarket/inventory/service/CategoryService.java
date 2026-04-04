package com.comunifield.micromarket.inventory.service;

import com.comunifield.micromarket.inventory.dto.CategoryDTO;
import com.comunifield.micromarket.inventory.entity.Category;

import java.util.List;

public interface CategoryService {

    Category create(CategoryDTO dto);

    List<Category> getAll();

    Category getById(Long id);

    Category update(Long id, CategoryDTO dto);

    void delete(Long id);
}