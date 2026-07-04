package com.cms.employee_service.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.cms.employee_service.dto.EmployeeMapper;
import com.cms.employee_service.dto.EmployeeRequestDto;
import com.cms.employee_service.dto.EmployeeResponseDto;
import com.cms.employee_service.dto.EmployeeSummaryDto;
import com.cms.employee_service.entity.Employee;
import com.cms.employee_service.exception.ResourceNotFoundException;
import com.cms.employee_service.repository.EmployeeRepository;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository repository;
    private EmployeeMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository repository,
                               EmployeeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public EmployeeResponseDto create(EmployeeRequestDto dto) {

        Employee employee = mapper.toEntity(dto);

        employee.setEmployeeCode("EMP" + System.currentTimeMillis());
        employee.setActive(true);
        employee.setCreatedAt(LocalDateTime.now());
        employee.setUpdatedAt(LocalDateTime.now());

        Employee savedEmployee = repository.save(employee);

        return mapper.toResponseDto(savedEmployee);
    }

    @Override
    public EmployeeResponseDto update(Long id, EmployeeRequestDto dto) {

        Employee employee = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id " + id));

        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setDesignation(dto.getDesignation());
        employee.setPhone(dto.getPhone());
        employee.setUpdatedAt(LocalDateTime.now());

        Employee updatedEmployee = repository.save(employee);

        return mapper.toResponseDto(updatedEmployee);
    }

    @Override
    public EmployeeResponseDto getById(Long id) {

        Employee employee = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id " + id));

        return mapper.toResponseDto(employee);
    }

    @Override
    public List<EmployeeResponseDto> getAll() {

        return repository.findAll()
                .stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {

        Employee employee = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id " + id));

        employee.setActive(false);
        employee.setUpdatedAt(LocalDateTime.now());

        repository.save(employee);
    }

    @Override
    public EmployeeSummaryDto getSummary(Long id) {

        Employee employee = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id " + id));

        return mapper.toSummaryDto(employee);
    }

    @Override
    public EmployeeResponseDto getByEmail(String email) {

        Employee employee = repository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with email: " + email));

        return mapper.toResponseDto(employee);
    }
}