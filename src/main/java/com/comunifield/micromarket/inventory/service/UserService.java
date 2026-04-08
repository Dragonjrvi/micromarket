package com.comunifield.micromarket.inventory.service;

import com.comunifield.micromarket.inventory.dto.UserResponseDTO;
import com.comunifield.micromarket.inventory.entity.Users;
import com.comunifield.micromarket.inventory.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;

    public List<UserResponseDTO> listarUsuarios() {
            return usersRepository.findAll()
                    .stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());
    }

    public UserResponseDTO toDto(Users u) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(u.getId());
        dto.setUsername(u.getUsername());
        dto.setEmail(u.getEmail());
        dto.setPassword(u.getPassword());
        return dto;
    }
}
