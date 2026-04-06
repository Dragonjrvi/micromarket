package com.comunifield.micromarket.inventory.dto;
import lombok.Data;

@Data
public class StockRequestDTO {
    private Long productId;
    private Long supplierId;
    private Integer quantity;
}