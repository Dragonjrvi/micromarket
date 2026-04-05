package com.comunifield.micromarket.inventory.service.impl;

import com.comunifield.micromarket.inventory.dto.ProductDTO;
import com.comunifield.micromarket.inventory.entity.*;
import com.comunifield.micromarket.inventory.repository.*;
import com.comunifield.micromarket.inventory.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;

    @Override
    public Product create(ProductDTO dto) {

        if (productRepo.findByBarcode(dto.getBarcode()).isPresent()) {
            throw new RuntimeException("El código de barras ya existe");
        }

        Category category = categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        Product p = new Product();
        p.setName(dto.getName());
        p.setBarcode(dto.getBarcode());
        p.setPrice(dto.getPrice());
        p.setStock(dto.getStock());
        p.setCategory(category);

        return productRepo.save(p);
    }

    @Override
    public Product update(Long id, ProductDTO dto) {

        Product p = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        p.setName(dto.getName());
        p.setPrice(dto.getPrice());
        p.setStock(dto.getStock());

        return productRepo.save(p);
    }

    @Override
    public void delete(Long id) {

        Product p = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        p.setActive(false);
        productRepo.save(p);
    }

    @Override
    public Product getById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public List<Product> getAll() {
        return productRepo.findAll()
                .stream()
                .filter(Product::getActive)
                .toList();
    }
}