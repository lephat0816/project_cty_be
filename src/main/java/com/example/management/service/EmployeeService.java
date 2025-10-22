package com.example.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.management.dto.EmployeeDTO;
import com.example.management.dto.UserDTO;
import com.example.management.entity.Employee;
import com.example.management.entity.User;
import com.example.management.mapper.EmployeeMapper;
import com.example.management.mapper.UserMapper;
import com.example.management.repository.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeDTO createUser(Employee newEmployee) {
        // パスワードを暗号化

        Employee employee = employeeRepository.save(newEmployee);
        return EmployeeMapper.toDTO(employee);

    }

    public EmployeeDTO getEmpById(Long id) {
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "ID: " + id + " の社員が見つかりません"));
        return EmployeeMapper.toDTO(employee);
    }

    public EmployeeDTO updateUser(Employee newEmployee, Long id) {
        Employee updatedEmp = employeeRepository.findById(id)
                .map(emp -> {
                    emp.setName(newEmployee.getName());
                    emp.setEmail(newEmployee.getEmail());
                    emp.setAddress(newEmployee.getAddress());
                    emp.setPhoneNumber(newEmployee.getPhoneNumber());
                    return employeeRepository.save(emp);
                })
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "ID: " + id + " のユーザーが見つかりません"));
        return EmployeeMapper.toDTO(updatedEmp);
    }
}
