package com.example.management.mapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.management.dto.UserDTO;
import com.example.management.entity.Role;
import com.example.management.entity.User;

public class UserMapper {
    // UserエンティティをUserDTOに変換
    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        Set<String> roleNames = user.getRoles().stream()
                                    .map(Role::getName)
                                    .collect(Collectors.toSet());
        return new UserDTO(user.getId(), user.getUsername(), user.getName(), user.getEmail(), roleNames);
    }
    
    public static UserDTO toDTO(Optional<User> userOpt) {
        if (userOpt.isEmpty()) {
            return null;
        }
        User user = userOpt.get();
        Set<String> roleNames = user.getRoles().stream()
                                    .map(Role::getName)
                                    .collect(Collectors.toSet());
        return new UserDTO(user.getId(), user.getUsername(), user.getName(), user.getEmail(), roleNames);
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
