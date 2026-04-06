package com.comunifield.micromarket.inventory.service.impl;

import com.comunifield.micromarket.inventory.dto.CategoryDTO;
import com.comunifield.micromarket.inventory.entity.Category;
import com.comunifield.micromarket.inventory.repository.CategoryRepository;
import com.comunifield.micromarket.inventory.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repo;

    @Override
    public Category create(CategoryDTO dto) {
        Category c = new Category();
        c.setName(dto.getName());
        return repo.save(c);
    }

    @Override
    public List<Category> getAll() {
        return repo.findAll();
    }

    @Override
    public Category getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    @Override
    public Category update(Long id, CategoryDTO dto) {
        Category c = getById(id);
        c.setName(dto.getName());
        return repo.save(c);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}