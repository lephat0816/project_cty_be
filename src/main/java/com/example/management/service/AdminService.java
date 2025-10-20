package com.example.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.management.dto.UserDTO;
import com.example.management.entity.User;
import com.example.management.mapper.UserMapper;
import com.example.management.repository.UserRepository;

@Service
public class AdminService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO login(String username, String rawPassword) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null && passwordEncoder.matches(rawPassword, user.getPassword())) {
            return UserMapper.toDTO(user);
        }
        return null;
    }

    public UserDTO register(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        User user = userRepository.save(newUser);
        return UserMapper.toDTO(user);
    }   
}
