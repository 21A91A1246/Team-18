package com.cms.employee_service.controller;


import java.util.List;

import com.cms.employee_service.dto.EmployeeRequestDto;
import com.cms.employee_service.dto.EmployeeResponseDto;
import com.cms.employee_service.dto.EmployeeSummaryDto;
import com.cms.employee_service.service.EmployeeService;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public EmployeeResponseDto createEmployee(
            @Valid @RequestBody EmployeeRequestDto dto) {

        return employeeService.create(dto);
    }

    @GetMapping
    public List<EmployeeResponseDto> getAllEmployees() {

        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public EmployeeResponseDto getEmployeeById(@PathVariable Long id) {

        return employeeService.getById(id);
    }

    @PutMapping("/{id}")
    public EmployeeResponseDto updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequestDto dto) {

        return employeeService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {

        employeeService.delete(id);

        return "Employee deactivated successfully.";
    }

    @GetMapping("/summary/{id}")
    public EmployeeSummaryDto getEmployeeSummary(@PathVariable Long id) {
        return employeeService.getSummary(id);
    }

    @GetMapping("/email/{email}")
    public EmployeeResponseDto getEmployeeByEmail(@PathVariable String email) {

        return employeeService.getByEmail(email);
    }

}