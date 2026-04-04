package com.comunifield.micromarket.inventory.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
public class CategoryDTO {

    @NotBlank
    private String name;
}