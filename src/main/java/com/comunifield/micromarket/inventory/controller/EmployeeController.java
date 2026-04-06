package com.comunifield.micromarket.inventory.controller;

import com.comunifield.micromarket.inventory.dto.EmployeeDTO;
import com.comunifield.micromarket.inventory.service.EmployeeService;
import com.comunifield.micromarket.inventory.entity.Employee;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody EmployeeDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @Valid @RequestBody EmployeeDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Empleado eliminado");
    }

    @GetMapping("/cargo/{cargo}")
    public ResponseEntity<?> getByCargo(@PathVariable Employee.Cargo cargo) {
        return ResponseEntity.ok(service.findByCargo(cargo));
    }

    @GetMapping("/fecha")
    public ResponseEntity<?> getByFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return ResponseEntity.ok(service.findByFechaIngreso(inicio, fin));
    }
}
