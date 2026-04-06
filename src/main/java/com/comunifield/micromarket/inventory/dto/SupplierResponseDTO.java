package com.comunifield.micromarket.inventory.dto;

import lombok.Data;

@Data
public class SupplierResponseDTO {
    private Long id;
    private String name;
    private String nit;
    private String phone;
    private String address;
}