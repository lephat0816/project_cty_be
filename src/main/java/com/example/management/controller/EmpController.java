package com.example.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.management.dto.EmployeeDTO;
import com.example.management.entity.Employee;
import com.example.management.service.EmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class EmpController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee/profile/{id}")
    public EmployeeDTO getEmpById(@PathVariable Long id) {
        return employeeService.getEmpById(id);
    }
    @PutMapping("/employee/profile/{id}")
    public EmployeeDTO updateUser(@RequestBody Employee newEmployee, @PathVariable Long id) {
        //  IDでユーザーを検索し、見つかれば更新し、保存する
        return employeeService.updateUser(newEmployee, id);
    }
}
