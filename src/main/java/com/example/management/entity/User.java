package com.example.management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    // ユーザーID (自動生成)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // ユーザー名（必須入力）
    @NotBlank(message = "ユーザー名の入力が必要です。")
    private String username;
    // ユーザーの名前（必須入力）
    @NotBlank(message = "名前の入力が必要です。")
    private String name;
    // メールアドレス（必須入力）
    @NotBlank(message = "メールアドレスの入力が必要です。")
    private String email;
    // パスワード（必須入力）
    @NotBlank(message = "パスワードの入力が必要です。")
    private String password;
}
