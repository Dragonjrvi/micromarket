package com.comunifield.micromarket.inventory.controller;


import com.comunifield.micromarket.inventory.dto.UserResponseDTO;
import com.comunifield.micromarket.inventory.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listarUsuarios() {

        List<UserResponseDTO> users = userService.listarUsuarios();
        return ResponseEntity.ok(users);
    }
}
