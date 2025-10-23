package com.example.management.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザー情報を表す DTO (Data Transfer Object)。
 *
 * このクラスはユーザー情報のやり取りに使用されます。
 */
@Data // Getter, Setter, toString, equals, hashCode を自動生成
@AllArgsConstructor // 全てのフィールドを受け取るコンストラクタを生成
@NoArgsConstructor // 引数なしのコンストラクタを生成
public class UserDTO {
    // @NotNull(message = "ユーザーIDは必須です。")
    private Long id;
    @NotBlank(message = "ユーザー名は必須です。")
    @Size(min = 3, max = 30, message = "ユーザー名は3文字以上30文字以内で入力してください")
    private String username;
    @NotBlank(message = "名前は必須です。")
    @Size(min = 2, max = 30, message = "名前は3文字以上30文字以内で入力してください")
    private String name;
    @NotBlank(message = "ユーザー名は必須です。")
    @Email(message = "有効なメールアドレスを入力してください。")
    private String email;
    @NotBlank(message = "パスワードの入力が必要です。")
    @Size(min = 6, max = 20, message = "パスワードは6文字以上20文字以内で入力してください")
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
    private String employeeId;
    private Set<String> roles; // ユーザーに付与されているロールのセット
}
