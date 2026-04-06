package com.comunifield.micromarket.inventory.dto;

import lombok.Data;

@Data
public class SupplierRequestDTO {
    private String name;
    private String nit;
    private String phone;
    private String address;
}
