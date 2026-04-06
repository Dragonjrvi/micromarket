package com.comunifield.micromarket.inventory.service.impl;

import com.comunifield.micromarket.inventory.dto.SaleRequestDTO;
import com.comunifield.micromarket.inventory.dto.SaleResponseDTO;
import com.comunifield.micromarket.inventory.dto.SaleDetailDTO;
import com.comunifield.micromarket.inventory.dto.ProductDTO;
import com.comunifield.micromarket.inventory.dto.EmployeeDTO;
import com.comunifield.micromarket.inventory.entity.*;
import com.comunifield.micromarket.inventory.repository.*;
import com.comunifield.micromarket.inventory.service.SaleService;

import java.time.LocalDateTime;
import java.util.ArrayList;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final EmployeeRepository employeeRepository;

    public SaleServiceImpl(SaleRepository saleRepository,
                           ProductRepository productRepository,
                           EmployeeRepository employeeRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public SaleResponseDTO createSale(SaleRequestDTO request) {

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        Sale sale = new Sale();
        sale.setDate(LocalDateTime.now());
        sale.setEmployee(employee);

        List<SaleDetail> details = new ArrayList<>();
        double subtotal = 0;

        for (SaleDetailDTO dto : request.getDetails()) {

            Product product = productRepository.findById(dto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            // VALIDACIONES
            if (!product.getActive()) {
                throw new RuntimeException("Producto inactivo: " + product.getName());
            }

            if (product.getStock() < dto.getQuantity()) {
                throw new RuntimeException("Stock insuficiente para: " + product.getName());
            }

            // ACTUALIZAR STOCK
            product.setStock(product.getStock() - dto.getQuantity());
            productRepository.save(product);

            // CREAR DETALLE
            SaleDetail detail = new SaleDetail();
            detail.setProduct(product);
            detail.setQuantity(dto.getQuantity());
            detail.setPrice(product.getPrice());

            double totalDetail = product.getPrice() * dto.getQuantity();
            detail.setTotal(totalDetail);

            subtotal += totalDetail;

            detail.setSale(sale);
            details.add(detail);
        }

        double iva = subtotal * 0.19;
        double total = subtotal + iva;

        sale.setSubtotal(subtotal);
        sale.setIva(iva);
        sale.setTotal(total);
        sale.setDetails(details);

        Sale saved = saleRepository.save(sale);

        return mapToDTO(saved);
    }

    @Override
    public List<SaleResponseDTO> getAllSales() {
        return saleRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public SaleResponseDTO getSaleById(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        return mapToDTO(sale);
    }

    // 🔁 MAPPER CORRECTO (CLAVE)
    private SaleResponseDTO mapToDTO(Sale sale) {

        SaleResponseDTO dto = new SaleResponseDTO();

        dto.setId(sale.getId());
        dto.setDate(sale.getDate());
        dto.setSubtotal(sale.getSubtotal());
        dto.setIva(sale.getIva());
        dto.setTotal(sale.getTotal());

        // 👇 IMPORTANTE: relación con employee
        dto.setId(sale.getEmployee().getId());

        return dto;
    }
}