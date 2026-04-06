package com.comunifield.micromarket.inventory.controller;

import com.comunifield.micromarket.inventory.dto.SaleResponseDTO;
import com.comunifield.micromarket.inventory.dto.SaleRequestDTO;
import com.comunifield.micromarket.inventory.service.ProductService;
import com.comunifield.micromarket.inventory.service.SaleService;
import lombok.RequiredArgsConstructor;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = Objects.requireNonNull(saleService);
    }

    @PostMapping
    public ResponseEntity<SaleResponseDTO> createSale(
            @Valid @RequestBody SaleRequestDTO request) {

        return ResponseEntity.ok(saleService.createSale(request));
    }
}