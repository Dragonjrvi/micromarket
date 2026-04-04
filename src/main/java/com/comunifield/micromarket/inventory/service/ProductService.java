package com.comunifield.micromarket.inventory.service;

import com.comunifield.micromarket.inventory.dto.ProductDTO;
import com.comunifield.micromarket.inventory.entity.Product;

import java.util.List;

public interface ProductService {

    Product create(ProductDTO dto);

    Product update(Long id, ProductDTO dto);

    void delete(Long id);

    Product getById(Long id);

    List<Product> getAll();
}