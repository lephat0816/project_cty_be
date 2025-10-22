package com.example.management.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

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
    private LocalDate birthday;
    @NotBlank(message = "性別の入力が必要です。")
    private String gender;
    
    private String photo;
    @NotBlank(message = "メールアドレスの入力が必要です。")
    private String email;
    @NotBlank(message = "電話番号の入力が必要です。")
    private String phoneNumber;
    @NotBlank(message = "住所の入力が必要です。")
    private String address;
    @NotBlank(message = "ポジションの入力が必要です。")
    private String position;
    @NotNull(message = "入社日の入力が必要です。")
    private LocalDate dateHired;
    @NotBlank(message = "状態の入力が必要です。")
    private String status;
}
