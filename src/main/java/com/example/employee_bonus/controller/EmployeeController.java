// src/main/java/com/example/employeebonus/controller/EmployeeController.java
package com.example.employee_bonus.controller;

import com.example.employee_bonus.entity.Employee;
import com.example.employee_bonus.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/tci")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee-bonus")
    public Map<String, String> saveEmployees(@RequestBody Map<String, List<Employee>> payload) {
        List<Employee> employees = payload.get("employees");
        employeeService.saveEmployees(employees);
        return Map.of("message", "Employees saved successfully");
    }


    @GetMapping("/employee-bonus")
    public Map<String, Object> getEligibleEmployees(@RequestParam("data") String date) {
        LocalDate requestDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
        List<Employee> eligibleEmployees = employeeService.getEligibleEmployees(requestDate);

        Map<String, List<Map<String, Object>>> groupedByCurrency = eligibleEmployees.stream()
                .collect(Collectors.groupingBy(Employee::getCurrency, Collectors.mapping(emp -> {
                    Map<String, Object> empData = new HashMap<>();
                    empData.put("empName", emp.getEmpName());
                    empData.put("amount", emp.getAmount());
                    return empData;
                }, Collectors.toList())));


        Map<String, Object> response = new HashMap<>();
        response.put("errorMessage", "");
        response.put("data", groupedByCurrency.entrySet().stream().map(entry -> {
            Map<String, Object> currencyGroup = new HashMap<>();
            currencyGroup.put("currency", entry.getKey());
            currencyGroup.put("employees", entry.getValue());
            return currencyGroup;
        }).collect(Collectors.toList()));

        return response;
    }
}
