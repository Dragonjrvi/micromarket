package com.comunifield.micromarket.inventory.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequestDTO {
    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String username;

    @NotBlank(message = "El correo electrónico es obligatorio")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
}
