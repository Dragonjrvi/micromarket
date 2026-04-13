package com.comunifield.micromarket.inventory.dto;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SupplierRequestDTO {
    @NotBlank(message = "El nombre del proveedor es obligatorio")
    private String name;
    @NotBlank(message = "El NIT del proveedor es obligatorio")
    private String nit;
    @NotBlank(message = "El teléfono del proveedor es obligatorio")
    private String phone;
    @NotBlank(message = "La dirección del proveedor es obligatoria")
    private String address;
}
