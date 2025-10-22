package com.example.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.management.dto.LoginResponse;
import com.example.management.dto.UserDTO;
import com.example.management.entity.Role;
import com.example.management.entity.User;
import com.example.management.mapper.UserMapper;
import com.example.management.repository.UserRepository;
import com.example.management.util.JWTUtil;

@Service
public class AdminService {
    // UserRepositoryをDI（依存性注入）で注入
    @Autowired
    private UserRepository userRepository;
    @Autowired
    // passwordEncoderをDI（依存性注入）で注入
    private PasswordEncoder passwordEncoder;
    // JWTトークン生成用ユーティリティ
    @Autowired
    private JWTUtil jwtUtil;

    /**
     * ユーザー名とパスワードでログイン処理を行う
     * @param username ユーザー名
     * @param rawPassword 入力された生パスワード
     * @return LoginRespose: トークンとユーザー情報を返す
     *         ログイン失敗時はnullを返す
     */
    public LoginResponse login(String username, String rawPassword) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null && passwordEncoder.matches(rawPassword, user.getPassword())) {
            String token = jwtUtil.generateToken(user.getUsername(), user.getId(), user.getRoles()
                                                                                        .stream()
                                                                                        .findFirst()
                                                                                        .map(Role::getName)
                                                                                        .orElse("user"));
            UserDTO userDTO = UserMapper.toDTO(user);
            return new LoginResponse(token, userDTO);
        }
        return null;
    }

    /**
     * 新規ユーザー登録処理
     * @param newUser 登録するユーザー情報（パスワードは平文）
     * @return 登録後のユーザー情報（DTO）
     */
    public UserDTO register(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        User user = userRepository.save(newUser);
        return UserMapper.toDTO(user);
    }   

    
}
