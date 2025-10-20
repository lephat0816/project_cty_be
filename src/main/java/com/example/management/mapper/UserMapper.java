package com.example.management.mapper;

import java.util.List;
import java.util.Optional;

import com.example.management.dto.UserDTO;
import com.example.management.entity.User;

public class UserMapper {
    // UserエンティティをUserDTOに変換
    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(user.getId(), user.getUsername(), user.getName(), user.getEmail());
    }
    
    public static UserDTO toDTO(Optional<User> userOpt) {
        if (userOpt.isEmpty()) {
            return null;
        }
        User user = userOpt.get();
        return new UserDTO(user.getId(), user.getUsername(), user.getName(), user.getEmail());
    }
    // UserエンティティのリストをUserDTOのリストに変換
    public static List<UserDTO> toDTOList(List<User> users) {
        return users.stream().map(UserMapper::toDTO).toList();
    }
    // UserDTOをUserエンティティに変換
    public static User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        return user;
    }
}
