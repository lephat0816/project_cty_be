package com.example.management.mapper;

import com.example.management.dto.EmployeeDTO;
import com.example.management.entity.Employee;

public class EmployeeMapper {
    public static EmployeeDTO toDTO (Employee employee) {
        if (employee == null) {
            return null;
        }
        return new EmployeeDTO(employee.getEmployeeId(), employee.getName(), employee.getBirthday(),
                            employee.getGender(), employee.getPhoto(), employee.getEmail(),
                            employee.getPhoneNumber(), employee.getAddress(), employee.getPosition(),
                            employee.getDateHired(), employee.getStatus());
    }

    public static Employee toEntity(EmployeeDTO employeeDTO) {
        if (employeeDTO == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setEmployeeId(employeeDTO.getEmployeeId());
        employee.setName(employeeDTO.getName());
        employee.setBirthday(employeeDTO.getBirthday());
        employee.setGender(employeeDTO.getGender());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setPosition(employeeDTO.getPosition());
        employee.setDateHired(employeeDTO.getDateHired());
        employee.setPhoto(employeeDTO.getPhoto());
        employee.setStatus(employeeDTO.getStatus());
        employee.setEmail(employeeDTO.getEmail());
        employee.setAddress(employeeDTO.getAddress());
        return employee;
    }
}
