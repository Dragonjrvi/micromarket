package com.comunifield.micromarket.inventory.service.impl;

import com.comunifield.micromarket.inventory.dto.EmployeeDTO;
import com.comunifield.micromarket.inventory.repository.EmployeeRepository;
import com.comunifield.micromarket.inventory.service.EmployeeService;
import com.comunifield.micromarket.inventory.entity.Employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repo;

    @Override
    public Employee create(EmployeeDTO dto) {
        if (repo.existsByCedula(dto.getCedula())) {
            throw new RuntimeException("Ya existe un empleado con esa cédula");
        }
        Employee e = new Employee();
        e.setCedula(dto.getCedula());
        e.setNombre(dto.getNombre());
        e.setCargo(dto.getCargo());
        e.setFechaIngreso(dto.getFechaIngreso());
        e.setSalario(dto.getSalario());
        return repo.save(e);
    }

    @Override
    public List<Employee> getAll() {
        return repo.findAll();
    }

    @Override
    public Employee getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
    }

    @Override
    public Employee update(Long id, EmployeeDTO dto) {
        Employee e = getById(id);
        e.setNombre(dto.getNombre());
        e.setCargo(dto.getCargo());
        e.setFechaIngreso(dto.getFechaIngreso());
        e.setSalario(dto.getSalario());
        return repo.save(e);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(getById(id).getId());
    }

    @Override
    public List<Employee> findByCargo(Employee.Cargo cargo) {
        return repo.findByCargo(cargo);
    }

    @Override
    public List<Employee> findByFechaIngreso(LocalDate inicio, LocalDate fin) {
        return repo.findByFechaIngresoBetween(inicio, fin);
    }
}
