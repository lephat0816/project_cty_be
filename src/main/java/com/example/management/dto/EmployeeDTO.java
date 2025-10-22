package com.example.management.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long employeeId;

    private String name;

    private LocalDate birthday;

    private String gender;

    private String photo;

    private String email;

    private String phoneNumber;

    private String address;

    private String position;

    private LocalDate dateHired;

    private String status;
}
