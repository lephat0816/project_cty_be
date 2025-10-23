package com.example.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ログイン成功時に返却されるレスポンスを表す DTO (Data Transfer Object)。
 *
 * このクラスはフロントエンドにトークンとユーザー情報を渡すために使用。
 */
@Data
@AllArgsConstructor
public class LoginResponse {
    private String token; // 認証に使用するJWTトークン
    private UserDTO userDTO; // ログインしたユーザーの情報
}
