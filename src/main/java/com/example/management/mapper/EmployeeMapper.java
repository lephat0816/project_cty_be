package com.example.management.mapper;

import java.util.List;

import com.example.management.dto.EmployeeDTO;
import com.example.management.dto.UserDTO;
import com.example.management.entity.Employee;
import com.example.management.entity.User;

/**
 * EmployeeエンティティとEmployeeDTO間の変換を行うマッパークラス。
 * 
 * 主にサービス層やコントローラー層で、DTOとエンティティ間のデータ変換に使用。
 */
public class EmployeeMapper {

    /**
     * EmployeeエンティティをEmployeeDTOに変換。
     *
     * @param employee 変換対象のEmployeeエンティティ
     * @return 変換後のEmployeeDTO。引数がnullの場合はnullを返す。
     */
    public static EmployeeDTO toDTO (Employee employee) {
        if (employee == null) {
            return null;
        }
        return new EmployeeDTO(employee.getEmployeeId(), employee.getName(), employee.getBirthday(),
                            employee.getGender(), employee.getPhoto(), employee.getEmail(),
                            employee.getPhoneNumber(), employee.getAddress(), employee.getPosition(),
                            employee.getDateHired(), employee.getStatus(), employee.getDepartmentId(),
                            employee.getManagerId());
    }

    /**
     * UserエンティティのリストをUserDTOのリストに変換。
     *
     * @param employees 変換対象のUserエンティティリスト
     * @return 変換後のUserDTOリスト
     */
    public static List<EmployeeDTO> toDTOList(List<Employee> employees) {
        return employees.stream().map(EmployeeMapper::toDTO).toList();
    }

    /**
     * EmployeeDTOをEmployeeエンティティに変換。
     *
     * @param employeeDTO 変換対象のEmployeeDTO
     * @return 変換後のEmployeeエンティティ。引数がnullの場合はnullを返す。
     */
    public static Employee toEntity(EmployeeDTO employeeDTO) {
        if (employeeDTO == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setEmployeeId(employeeDTO.getEmployeeId());
        employee.setName(employeeDTO.getName());
        employee.setBirthday(employeeDTO.getBirthday());
        employee.setGender(employeeDTO.getGender());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setPosition(employeeDTO.getPosition());
        employee.setDateHired(employeeDTO.getDateHired());
        employee.setPhoto(employeeDTO.getPhoto());
        employee.setStatus(employeeDTO.getStatus());
        employee.setEmail(employeeDTO.getEmail());
        employee.setAddress(employeeDTO.getAddress());
        return employee;
    }
}
