package com.example.employee_bonus.service;


import com.example.employee_bonus.entity.Employee;
import com.example.employee_bonus.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;



    public void saveEmployees(List<Employee> employees) {
        employeeRepository.saveAll(employees);
    }

    public List<Employee> getEligibleEmployees(LocalDate date) {
        return employeeRepository.findByExitDateAfter(date);
    }
}