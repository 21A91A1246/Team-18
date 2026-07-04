package com.cms.employee_service.service;

import com.cms.employee_service.dto.EmployeeRequestDto;
import com.cms.employee_service.dto.EmployeeResponseDto;
import com.cms.employee_service.dto.EmployeeSummaryDto;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDto create(EmployeeRequestDto dto);

    EmployeeResponseDto update(Long id, EmployeeRequestDto dto);

    EmployeeResponseDto getById(Long id);

    List<EmployeeResponseDto> getAll();

    void delete(Long id);

    EmployeeSummaryDto getSummary(Long id);

    EmployeeResponseDto getByEmail(String email);
}