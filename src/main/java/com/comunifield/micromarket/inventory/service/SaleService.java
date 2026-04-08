package com.comunifield.micromarket.inventory.service;

import com.comunifield.micromarket.inventory.dto.SaleResponseDTO;
import com.comunifield.micromarket.inventory.dto.SaleRequestDTO;

import java.util.List;

public interface SaleService {

    SaleResponseDTO createSale(SaleRequestDTO request);

    List<SaleResponseDTO> getAllSales();

    SaleResponseDTO getSaleById(Long id);
}