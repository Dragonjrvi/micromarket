package com.comunifield.micromarket.inventory.controller;


import com.comunifield.micromarket.inventory.dto.StockRequestDTO;
import com.comunifield.micromarket.inventory.dto.SupplierRequestDTO;
import com.comunifield.micromarket.inventory.dto.SupplierResponseDTO;
import com.comunifield.micromarket.inventory.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public ResponseEntity<SupplierResponseDTO> create(@RequestBody SupplierRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(supplierService.createSupplier(dto));
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponseDTO>> list() {
        return ResponseEntity.ok(supplierService.listSuppliers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> get(@PathVariable Long id) {
        return supplierService.getSupplierById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> update(
            @RequestBody SupplierRequestDTO dto,
            @PathVariable Long id) {

        return supplierService.updateSupplier(dto, id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> delete(@PathVariable Long id) {
        return supplierService.deleteSupplier(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/stock")
    public ResponseEntity<String> addStock(@RequestBody StockRequestDTO dto) {
        supplierService.addStock(dto);
        return ResponseEntity.ok("Stock actualizado correctamente");
    }

    
}