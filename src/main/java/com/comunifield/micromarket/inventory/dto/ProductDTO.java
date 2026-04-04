package com.comunifield.micromarket.inventory.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter
public class ProductDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String barcode;

    @NotNull
    private Double price;

    @NotNull
    private Integer stock;

    @NotNull
    private Long categoryId;
}