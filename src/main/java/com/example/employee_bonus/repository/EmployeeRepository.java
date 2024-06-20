package com.example.employee_bonus.repository;

import com.example.employee_bonus.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByExitDateAfter(LocalDate date);
}
