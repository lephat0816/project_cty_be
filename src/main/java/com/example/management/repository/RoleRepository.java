package com.example.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.management.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
    
}
