package com.comunifield.micromarket.inventory.controller;
import com.comunifield.micromarket.security.JwtService;
import com.comunifield.micromarket.inventory.dto.ProductDTO;
import com.comunifield.micromarket.inventory.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final JwtService jwtService = new JwtService();
    private final ProductService service;

    @PostMapping
public ResponseEntity<?> create(@Valid @RequestBody ProductDTO dto) {

    String token = jwtService.generarToken("admin");
    System.out.println("Token generado: " + token);

    return ResponseEntity.ok(service.create(dto));
}

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @Valid @RequestBody ProductDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Producto desactivado");
    }
}
