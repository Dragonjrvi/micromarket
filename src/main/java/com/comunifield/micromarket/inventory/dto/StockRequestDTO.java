package com.comunifield.micromarket.inventory.dto;
import lombok.Data;
import jakarta.validation.constraints.*;
@Data
public class StockRequestDTO {
    @NotNull(message = "El ID del producto es obligatorio")
    private Long productId;
    @NotNull(message = "El ID del proveedor es obligatorio")
    private Long supplierId;
    @NotNull(message = "La cantidad es obligatoria")
    private Integer quantity;
}