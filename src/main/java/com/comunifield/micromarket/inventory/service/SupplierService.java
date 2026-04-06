package com.comunifield.micromarket.inventory.service;

import com.comunifield.micromarket.inventory.dto.SupplierRequestDTO;
import com.comunifield.micromarket.inventory.dto.SupplierResponseDTO;
import com.comunifield.micromarket.inventory.dto.StockRequestDTO;
import com.comunifield.micromarket.inventory.entity.Product;
import com.comunifield.micromarket.inventory.entity.Supplier;
import com.comunifield.micromarket.inventory.repository.ProductRepository;
import com.comunifield.micromarket.inventory.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;

    // 🔹 CREATE
    public SupplierResponseDTO createSupplier(SupplierRequestDTO dto) {

        if (supplierRepository.existsByNit(dto.getNit())) {
            throw new RuntimeException("El NIT ya existe");
        }

        Supplier supplier = new Supplier();
        supplier.setName(dto.getName());
        supplier.setNit(dto.getNit());
        supplier.setPhone(dto.getPhone());
        supplier.setAddress(dto.getAddress());

        supplierRepository.save(supplier);

        return toDto(supplier);
    }

    public List<SupplierResponseDTO> listSuppliers() {
        return supplierRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Optional<SupplierResponseDTO> getSupplierById(Long id) {
        if (id == null) {
            throw new RuntimeException("El id no puede ser null");
        }
        return supplierRepository.findById(id).map(this::toDto);
    }


    public Optional<SupplierResponseDTO> updateSupplier(SupplierRequestDTO dto, Long id) {

        
        if (id == null) {
            throw new RuntimeException("El id no puede ser null");
        }

        Optional<Supplier> supplierOpt = supplierRepository.findById(id);

        if (supplierOpt.isPresent()) {
            Supplier supplier = supplierOpt.get();

            supplier.setName(dto.getName());
            supplier.setNit(dto.getNit());
            supplier.setPhone(dto.getPhone());
            supplier.setAddress(dto.getAddress());

            supplierRepository.save(supplier);

            return Optional.of(toDto(supplier));
        }

        return Optional.empty();
    }


    public Optional<SupplierResponseDTO> deleteSupplier(Long id) {

        if (id == null) {
            throw new RuntimeException("El id no puede ser null");
        }
        Optional<Supplier> supplierOpt = supplierRepository.findById(id);

        if (supplierOpt.isPresent()) {
            supplierRepository.deleteById(id);
            return Optional.of(toDto(supplierOpt.get()));
        }

        return Optional.empty();
    }

    private SupplierResponseDTO toDto(Supplier supplier) {
        SupplierResponseDTO dto = new SupplierResponseDTO();
        dto.setId(supplier.getId());
        dto.setName(supplier.getName());
        dto.setNit(supplier.getNit());
        dto.setPhone(supplier.getPhone());
        dto.setAddress(supplier.getAddress());
        return dto;
    }


    public void addStock(StockRequestDTO dto) {

    if (dto.getQuantity() <= 0) {
        throw new RuntimeException("Cantidad inválida");
    }

    Product product = productRepository.findById(dto.getProductId())
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

    Supplier supplier = supplierRepository.findById(dto.getSupplierId())
            .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

    if (!product.getSuppliers().contains(supplier)) {
        product.getSuppliers().add(supplier);
    }

    product.setStock(product.getStock() + dto.getQuantity());
    if (product.getStock() > 0) {
        product.setActive(true);
    }

    productRepository.save(product);
}
}