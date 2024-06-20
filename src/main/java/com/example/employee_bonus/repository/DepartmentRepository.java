package com.example.employee_bonus.repository;

import com.example.employee_bonus.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
