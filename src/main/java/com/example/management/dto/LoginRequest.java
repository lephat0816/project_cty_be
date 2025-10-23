package com.example.management.dto;

import lombok.Data;

/**
 * ログイン時にフロントエンドから送信されるリクエストを表す DTO (Data Transfer Object)。
 *
 * このクラスはユーザー名とパスワードをサーバーに送るために使用されます。
 */
@Data
public class LoginRequest {
    private String username;
    private String password;
}
