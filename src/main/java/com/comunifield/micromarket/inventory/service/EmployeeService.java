package com.comunifield.micromarket.inventory.service;

import com.comunifield.micromarket.inventory.dto.EmployeeDTO;
import com.comunifield.micromarket.inventory.entity.Employee;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {

    Employee create(EmployeeDTO dto);

    List<Employee> getAll();

    Employee getById(Long id);

    Employee update(Long id, EmployeeDTO dto);

    void delete(Long id);

    List<Employee> findByCargo(Employee.Cargo cargo);

    List<Employee> findByFechaIngreso(LocalDate inicio, LocalDate fin);
}
