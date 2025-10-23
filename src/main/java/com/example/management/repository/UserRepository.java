package com.example.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.management.entity.User;

/**
 * ユーザーエンティティのデータアクセスを提供するリポジトリインターフェース。
 * 
 * Spring Data JPAを継承しており、基本的なCRUD操作やカスタムクエリが使用可能。
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}