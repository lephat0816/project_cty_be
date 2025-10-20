package com.example.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getter, Setter, toString, equals, hashCode を自動生成
@AllArgsConstructor // 全てのフィールドを受け取るコンストラクタを生成
@NoArgsConstructor // 引数なしのコンストラクタを生成
public class UserDTO {
    // ユーザーID
    private Long id;
    // ユーザー名
    private String username;
    // ユーザーの名前
    private String name;
    // メールアドレス
    private String email;
}
