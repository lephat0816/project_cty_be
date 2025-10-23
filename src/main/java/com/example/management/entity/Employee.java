package com.example.management.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
/**
 * Employeeの情報を表すDTO (Data Transfer Object)。
 *
 * このクラスはフロントエンドとバックエンド間で従業員データをやり取りするために使用。
 */
@Entity
@Table(name = "employees")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    @NotBlank(message = "名前の入力が必要です。")
    private String name;
    @NotNull(message = "生年月日の入力が必要です。")
    @Column(name = "birth_date")
    private LocalDate birthday;
    @NotBlank(message = "性別の入力が必要です。")
    private String gender;
    private String photo;
    @NotBlank(message = "メールアドレスの入力が必要です。")
    private String email;
    @NotBlank(message = "電話番号の入力が必要です。")
    @Column(name = "phone")
    private String phoneNumber;
    @NotBlank(message = "住所の入力が必要です。")
    private String address;
    @NotBlank(message = "ポジションの入力が必要です。")
    private String position;
    @NotNull(message = "入社日の入力が必要です。")
    @Column(name = "hire_date")
    private LocalDate dateHired;
    @NotBlank(message = "状態の入力が必要です。")
    private String status;
    @Column(name = "department_id")
    private Integer departmentId;
    @Column(name = "manager_id")
    private Integer managerId;
}
