package com.example.management.mapper;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.management.dto.UserDTO;
import com.example.management.entity.Role;
import com.example.management.entity.User;

/**
 * UserエンティティとUserDTO間の変換を行うマッパークラス。
 * 
 * 主にサービス層やコントローラー層で、DTOとエンティティ間のデータ変換に使用される。
 */

public class UserMapper {
    /**
     * UserエンティティをUserDTOに変換。
     *
     * @param user 変換対象のUserエンティティ
     * @return 変換後のUserDTO。引数がnullの場合はnullを返す。
     */
    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        Set<String> roleNames = (user.getRoles() != null) ?
                                    user.getRoles().stream()
                                        .map(Role::getName)
                                        .collect(Collectors.toSet()) :
                                    Collections.emptySet();
        return new UserDTO(user.getId()
                            , user.getUsername()
                            , user.getName()
                            , user.getEmail()
                            , user.getPassword()
                            , user.getEmployeeId()
                            , roleNames);
    }
    
    /**
     * Optional<User>をUserDTOに変換。
     *
     * @param userOpt 変換対象のOptional<User>
     * @return 変換後のUserDTO。引数が空の場合はnullを返す。
     */
    public static UserDTO toDTO(Optional<User> userOpt) {
        if (userOpt.isEmpty()) {
            return null;
        }
        User user = userOpt.get();
        Set<String> roleNames = (user.getRoles() != null) ?
                                    user.getRoles().stream()
                                        .map(Role::getName)
                                        .collect(Collectors.toSet()) :
                                    Collections.emptySet();
        return new UserDTO(user.getId()
                    , user.getUsername()
                    , user.getName()
                    , user.getEmail()
                    ,user.getPassword()
                    , user.getEmployeeId()
                    , roleNames
                    );
    }
    /**
     * UserエンティティのリストをUserDTOのリストに変換。
     *
     * @param users 変換対象のUserエンティティリスト
     * @return 変換後のUserDTOリスト
     */
    public static List<UserDTO> toDTOList(List<User> users) {
        return users.stream().map(UserMapper::toDTO).toList();
    }
    /**
     * UserDTOをUserエンティティに変換。
     *
     * @param userDTO 変換対象のUserDTO
     * @return 変換後のUserエンティティ。引数がnullの場合はnullを返す。
     */
    public static User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setEmployeeId(userDTO.getEmployeeId());
        return user;
    }
}
