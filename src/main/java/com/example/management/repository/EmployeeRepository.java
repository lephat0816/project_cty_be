package com.example.management.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.management.entity.Employee;


/**
 * Employeeエンティティのデータアクセスを提供するリポジトリインターフェース。
 * 
 * Spring Data JPAを継承しており、基本的なCRUD操作やカスタムクエリが使用可能。
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
}
