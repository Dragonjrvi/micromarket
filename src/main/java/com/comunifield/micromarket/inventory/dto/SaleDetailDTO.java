package com.comunifield.micromarket.inventory.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.Min;

@Data
public class SaleDetailDTO {
    @NotNull(message = "Product ID is required")
    private Long productId;

    @NotNull(message = "The quantity is mandatory")
    @Min(value = 1, message = "The amount must be greater than 0")
    private Integer quantity;
}
