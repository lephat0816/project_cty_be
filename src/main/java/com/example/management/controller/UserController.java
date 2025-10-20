package com.example.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.management.dto.LoginRequest;
import com.example.management.dto.UserDTO;
import com.example.management.entity.User;
import com.example.management.service.AdminService;
import com.example.management.service.UserService;
import org.springframework.http.HttpStatus;


@RestController
// クロスオリジンリクエストを許可
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    // UserRepositoryのインスタンスを注入
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    // 新しいユーザーを作成するエンドポイント
    @PostMapping("/user")
    public UserDTO newUser(@RequestBody User newUser) {
        // 新しいユーザーを作成するエンドポイント
        return userService.createUser(newUser);
    }
    // 全てのユーザーを取得するエンドポイント
    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        // ユーザーリストを取得
        return userService.getAllUsers();
    }
    // IDでユーザーを取得するエンドポイント
    @GetMapping("/user/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        // IDでユーザーを検索し、見つからなければ404エラーをスロー
        return userService.getUserById(id);
    }
    // ユーザー情報を更新するエンドポイント
    @PutMapping("/user/{id}")
    public UserDTO updateUser(@RequestBody User newUser, @PathVariable Long id) {
        //  IDでユーザーを検索し、見つかれば更新し、保存する
        return userService.updateUser(newUser, id);

    }
    // ユーザーを削除するエンドポイント
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable Long id) {
        // ユーザーが存在しない場合は404エラーをスロー
        return userService.deleteUser(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        UserDTO user = adminService.login(loginRequest.getUsername(),loginRequest.getPassword());
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body("ユーザー名またはパスワードが正しくありません");                   
    }

    @PostMapping("/register")
    public UserDTO register(@RequestBody User newUser) {
        // 新しいユーザーを作成するエンドポイント
        return adminService.register(newUser);
    }
    
}
