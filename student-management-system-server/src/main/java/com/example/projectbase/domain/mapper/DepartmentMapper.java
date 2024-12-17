package com.example.projectbase.domain.mapper;

import com.example.projectbase.domain.dto.request.DepartmentCreateDto;
import com.example.projectbase.domain.dto.response.DepartmentResponseDto;
import com.example.projectbase.domain.entity.Department;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    Department toEntity(DepartmentCreateDto departmentCreateDto);
    DepartmentResponseDto toDepartmentResponseDto(Department department);
    List<DepartmentResponseDto> toDepartmentResponseDtoList(List<Department> departmentList);
}
