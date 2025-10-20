package com.example.management.service;

import com.example.management.dto.UserDTO;
import com.example.management.entity.User;
import com.example.management.mapper.UserMapper;
import com.example.management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // 新しいユーザーを作成し、DTO形式で返す
    public UserDTO createUser(User newUser) {
        // パスワードを暗号化
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        User user = userRepository.save(newUser);
        return UserMapper.toDTO(user);
        
    }

    // すべてのユーザー情報をDTO形式で取得
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return UserMapper.toDTOList(users);
    }

    // IDでユーザーを検索し、見つからなければ404エラーをスロー
    public UserDTO getUserById(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "ID: " + id + " のユーザーが見つかりません"));
        return UserMapper.toDTO(user);
    }

    // IDでユーザーを検索し、見つかれば更新し、保存する
    public UserDTO updateUser(User newUser, Long id) {
        User updatedUser = userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "ID: " + id + " のユーザーが見つかりません"));
        return UserMapper.toDTO(updatedUser);
    }

    // ユーザーが存在しない場合は404エラーをスロー
    public String deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "ID: " + id + " のユーザーが見つかりません");
        }
        userRepository.deleteById(id);
        return "ID: " + id + " のユーザーは正常に削除されました";
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "ユーザーが見つかりません"));
    }
}
