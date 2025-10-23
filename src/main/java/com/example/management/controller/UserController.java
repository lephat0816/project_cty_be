package com.example.management.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.management.dto.EmployeeDTO;
import com.example.management.dto.LoginRequest;
import com.example.management.dto.LoginResponse;
import com.example.management.dto.UserDTO;
import com.example.management.entity.Employee;
import com.example.management.service.AdminService;
import com.example.management.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;

/**
 * ユーザー管理用RESTコントローラー。
 * このクラスではユーザーの作成、取得、更新、削除、
 * ログイン及び登録機能を提供。
 */
@RestController
// クロスオリジンリクエストを許可
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // UserRepositoryのインスタンスを注入
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    /**
     * ユーザーのログイン。
     *
     * @param loginRequest ログイン情報（ユーザー名とパスワード）
     * @return 成功時は LoginResponse、失敗時は 401 エラー
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        logger.info("ロギング試行： ユーザー名 = {}", loginRequest.getUsername());
        LoginResponse response = adminService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("ユーザー名またはパスワードが正しくありません");
    }

    /**
     * 新しいユーザーを登録。
     *
     * @param newUser 登録するユーザー情報
     * @return 登録されたユーザー情報を格納した UserDTO
     */

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO savedUserDTO = adminService.registerUser(userDTO);
        return ResponseEntity.ok(savedUserDTO);
    }

    /**
     * 全てのユーザーを取得。
     * 
     * @return ユーザー一覧
     */
    @GetMapping("/users")
    public List<EmployeeDTO> getAllEmps() {
        return adminService.getAllEmpls();
    }

    /**
     * 指定された ID のユーザーを削除。
     * 管理者権限が必要です。
     *
     * @param id ユーザーの ID
     * @return 削除結果のメッセージ
     */
    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
    
}
