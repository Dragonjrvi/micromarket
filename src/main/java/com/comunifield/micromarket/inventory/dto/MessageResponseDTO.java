package com.comunifield.micromarket.inventory.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class MessageResponseDTO {
    @NotBlank(message = "El mensaje es obligatorio")
    private String message;
}
