package com.cms.employee_service.dto;



import com.cms.employee_service.entity.Employee;
import org.springframework.stereotype.Component;


@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequestDto dto) {

        Employee employee = new Employee();

        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setDesignation(dto.getDesignation());
        employee.setPhone(dto.getPhone());

        return employee;
    }

    public EmployeeResponseDto toResponseDto(Employee employee) {

        EmployeeResponseDto dto = new EmployeeResponseDto();

        dto.setId(employee.getId());
        dto.setEmployeeCode(employee.getEmployeeCode());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setDepartment(employee.getDepartment());
        dto.setDesignation(employee.getDesignation());
        dto.setPhone(employee.getPhone());
        dto.setActive(employee.getActive());

        return dto;
    }

    public EmployeeSummaryDto toSummaryDto(Employee employee) {

        EmployeeSummaryDto dto = new EmployeeSummaryDto();

        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setDepartment(employee.getDepartment());

        return dto;
    }

}