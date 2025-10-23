package com.example.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.management.dto.EmployeeDTO;
import com.example.management.dto.UserDTO;
import com.example.management.entity.Employee;
import com.example.management.entity.User;
import com.example.management.mapper.EmployeeMapper;
import com.example.management.mapper.UserMapper;
import com.example.management.repository.EmployeeRepository;

/**
 * Employee情報に関するサービスクラス。
 * データベース操作を行い、DTOへの変換も担当。
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 新しい社員を作成し、保存。
     *
     * @param newEmployee 作成する社員エンティティ
     * @return 作成された社員のDTO
     */
    public EmployeeDTO createEmployee(EmployeeDTO newEmployeeDTO) {
        Employee newEmployee = EmployeeMapper.toEntity(newEmployeeDTO);

        Employee createEmployee = employeeRepository.save(newEmployee);
        return EmployeeMapper.toDTO(createEmployee);

    }
    /**
     * IDを指定して社員情報を取得。
     *
     * @param id 社員ID
     * @return 指定IDの社員DTO
     * @throws ResponseStatusException 社員が存在しない場合、404エラーを返す
     */
    public EmployeeDTO getEmpById(Long id) {
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "ID: " + id + " の社員が見つかりません"));
        return EmployeeMapper.toDTO(employee);
    }
    /**
     * 指定IDの社員情報を更新。
     *
     * @param newEmployee 更新する社員情報
     * @param id 更新対象の社員ID
     * @return 更新後の社員DTO
     * @throws ResponseStatusException 社員が存在しない場合、404エラーを返す
     */
    public EmployeeDTO updateEmp(EmployeeDTO newEmployeeDTO, Long id) {
        Employee newEmployee = EmployeeMapper.toEntity(newEmployeeDTO);
        Employee updatedEmp = employeeRepository.findById(id)
                .map(emp -> {
                    emp.setName(newEmployee.getName());
                    emp.setEmail(newEmployee.getEmail());
                    emp.setAddress(newEmployee.getAddress());
                    emp.setPhoneNumber(newEmployee.getPhoneNumber());
                    return employeeRepository.save(emp);
                })
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "ID: " + id + " のユーザーが見つかりません"));
        return EmployeeMapper.toDTO(updatedEmp);
    }

    
}
