package com.example.management.service;

import com.example.management.dto.UserDTO;
import com.example.management.entity.User;
import com.example.management.mapper.UserMapper;
import com.example.management.repository.RoleRepository;
import com.example.management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * ユーザー情報に関するサービスクラス。
 * CRUD操作およびDTO変換を担当。
 */
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    



    /**
     * 指定IDのユーザーを削除。
     *
     * @param id 削除対象のユーザーID
     * @return 削除完了メッセージ
     * @throws ResponseStatusException ユーザーが存在しない場合、404エラー
     */
    public String deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "ID: " + id + " のユーザーが見つかりません");
        }
        userRepository.deleteById(id);
        return "ID: " + id + " のユーザーは正常に削除されました";
    }

    /**
     * ユーザー名で検索してユーザーエンティティを取得。
     *
     * @param username ユーザー名
     * @return ユーザーエンティティ
     * @throws ResponseStatusException ユーザーが存在しない場合、404エラー
     */
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "ユーザーが見つかりません"));
    }

    
}
