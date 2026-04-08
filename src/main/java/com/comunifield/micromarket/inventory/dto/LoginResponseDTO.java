package com.comunifield.micromarket.inventory.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String message; 
    private String jwt;
}
