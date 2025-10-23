package com.example.management.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 従業員の情報を表すDTO (Data Transfer Object)。
 *
 * このクラスはフロントエンドとバックエンド間で従業員データをやり取りするために使用されます。
 */
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

    private Integer departmentId;

    private Integer managerId;
}
