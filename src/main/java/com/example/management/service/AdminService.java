package com.example.management.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.management.dto.EmployeeDTO;
import com.example.management.dto.LoginResponse;
import com.example.management.dto.UserDTO;
import com.example.management.entity.Employee;
import com.example.management.entity.Role;
import com.example.management.entity.User;
import com.example.management.mapper.EmployeeMapper;
import com.example.management.mapper.UserMapper;
import com.example.management.repository.EmployeeRepository;
import com.example.management.repository.RoleRepository;
import com.example.management.repository.UserRepository;
import com.example.management.util.JWTUtil;

@Service
public class AdminService {

    // UserRepositoryをDI（依存性注入）で注入
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;
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
    public UserDTO registerUser(UserDTO newUserDto) {
        User newUser = UserMapper.toEntity(newUserDto);
        newUser.setPassword(passwordEncoder.encode(newUserDto.getPassword()));
        if (newUser.getRoles() == null) {
            newUser.setRoles(new HashSet<>());
        }
        String roleName = (newUserDto.getRoles() != null && !newUserDto.getRoles().isEmpty())
                ? newUserDto.getRoles().iterator().next()
                : "user";
        Role role = roleRepository.findById(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
        newUser.getRoles().add(role);
        User savedUser = userRepository.save(newUser);
        return UserMapper.toDTO(savedUser);
    }

    /**
     * すべてのユーザー情報を取得。
     *
     * @return ユーザーDTOのリスト
     */
    public List<EmployeeDTO> getAllEmpls() {
        List<Employee> employees = employeeRepository.findAll();
        return EmployeeMapper.toDTOList(employees);
    }
    
}
