package com.comunifield.micromarket.personnel.service.impl;

import com.comunifield.micromarket.personnel.dto.EmployeeDTO;
import com.comunifield.micromarket.personnel.entity.Employee;
import com.comunifield.micromarket.personnel.repository.EmployeeRepository;
import com.comunifield.micromarket.personnel.service.EmployeeService;
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
