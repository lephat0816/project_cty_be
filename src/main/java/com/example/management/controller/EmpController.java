package com.example.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.management.dto.EmployeeDTO;

import com.example.management.service.EmployeeService;

/**
 * Employee情報に関するRESTコントロール。
 * CORS 設定により、フロントエンド（http://localhost:3000）からのリクエストを許可。
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class EmpController {
    @Autowired
    private EmployeeService employeeService;
    /**
     * 指定されたIDのemployee情報を取得。
     * @param id
     * @return　EmployeeDTO
     */
    @GetMapping("/employee/profile/{id}")
    public EmployeeDTO getEmpById(@PathVariable Long id) {
        return employeeService.getEmpById(id);
    }
    /**
     * 指定されたIDのemployee情報を更新。
     * @param newEmployee
     * @param id
     * @return EmployeeDTO
     */
    @PutMapping("/employee/profile/{id}")
    public EmployeeDTO updateEmp(@RequestBody EmployeeDTO newEmployeeDTO, @PathVariable Long id) {
        //  IDでユーザーを検索し、見つかれば更新し、保存する
        return employeeService.updateEmp(newEmployeeDTO, id);
    }
/**
     * 新しいEmployeeを作成
     * 
     * @param employeeDTO 作成するEmployee情報
     * @return EmployeeDTO
     */
    @PostMapping("/user")
    public ResponseEntity<EmployeeDTO> createEmp(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO createdEmp = employeeService.createEmployee(employeeDTO);
        return ResponseEntity.ok(createdEmp);
    }

    
}
