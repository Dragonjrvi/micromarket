package com.comunifield.micromarket.inventory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comunifield.micromarket.inventory.dto.LoginRequestDTO;
import com.comunifield.micromarket.inventory.dto.LoginResponseDTO;
import com.comunifield.micromarket.inventory.dto.MessageResponseDTO;
import com.comunifield.micromarket.inventory.dto.RefreshTokenDTO;
import com.comunifield.micromarket.inventory.dto.RegisterRequestDTO;
import com.comunifield.micromarket.inventory.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<MessageResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        try {
            MessageResponseDTO response = authService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        try {
            LoginResponseDTO response = authService.login(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }
    }

    @GetMapping("/refresh")
    public ResponseEntity<RefreshTokenDTO> refresh(HttpServletRequest request) {
        // Creamos la respuesta vacía
        RefreshTokenDTO response = new RefreshTokenDTO();

        // Obtenemos el header de "Authorization"
        String autheader = request.getHeader("Authorization");

        // Validamos que en la petición nos llegue el authorization
        if (autheader == null || !autheader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        // Obtenemos el token sin el bearer
        String token = autheader.substring(7);

        try {
            // Llamamos al servicio para retornar la respuesta
            response = authService.refreshToken(token);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            // Atrapamos alguna exception
            response.setMessage("Token expirado");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
