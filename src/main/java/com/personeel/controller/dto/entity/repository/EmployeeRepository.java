package com.comunifield.micromarket.personnel.repository;

import com.comunifield.micromarket.personnel.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsByCedula(String cedula);

    List<Employee> findByCargo(Employee.Cargo cargo);

    List<Employee> findByFechaIngresoBetween(LocalDate inicio, LocalDate fin);
}