package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.DepartmentCreateDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.DepartmentResponseDto;

import java.util.List;

public interface DepartmentService {
    DepartmentResponseDto createDepartment(DepartmentCreateDto departmentCreateDto);
    List<DepartmentResponseDto> getAllDepartments();
    DepartmentResponseDto findDepartmentById(String id);
    DepartmentResponseDto updateDepartment(String departmentId,DepartmentCreateDto departmentUpdateDto);
    CommonResponseDto deleteDepartment(String id);
}
