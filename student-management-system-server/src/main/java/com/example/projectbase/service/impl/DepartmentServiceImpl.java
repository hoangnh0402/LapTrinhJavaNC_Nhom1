package com.example.projectbase.service.impl;

import com.example.projectbase.constant.CommonConstant;
import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.domain.dto.request.DepartmentCreateDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.DepartmentResponseDto;
import com.example.projectbase.domain.entity.Department;
import com.example.projectbase.domain.mapper.DepartmentMapper;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.DepartmentRepository;
import com.example.projectbase.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public DepartmentResponseDto createDepartment(DepartmentCreateDto departmentCreateDto) {
        Department department = departmentMapper.toEntity(departmentCreateDto);
        return departmentMapper.toDepartmentResponseDto(departmentRepository.save(department));
    }

    @Override
    public List<DepartmentResponseDto> getAllDepartments() {
        return departmentMapper.toDepartmentResponseDtoList(departmentRepository.findAll());
    }

    @Override
    public DepartmentResponseDto findDepartmentById(String id) {
        return departmentMapper.toDepartmentResponseDto(departmentRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.Department.ERR_NOT_FOUND_ID, new String[]{id})));
    }

    @Override
    public DepartmentResponseDto updateDepartment(String departmentId, DepartmentCreateDto departmentUpdateDto) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new NotFoundException(ErrorMessage.Department.ERR_NOT_FOUND_ID, new String[]{departmentId}));
        if (departmentUpdateDto.getName() != null) {
            department.setName(departmentUpdateDto.getName());
        }
        if (departmentUpdateDto.getDescription() != null) {
            department.setDescription(departmentUpdateDto.getDescription());
        }
        if (departmentUpdateDto.getCode() != null)
            department.setCode(departmentUpdateDto.getCode());
        if (departmentUpdateDto.getDean() != null)
            department.setDean(departmentUpdateDto.getDean());
        if (departmentUpdateDto.getPhoneNumber() != null)
            department.setPhoneNumber(departmentUpdateDto.getPhoneNumber());
        if (departmentUpdateDto.getEmail() != null)
            department.setEmail(departmentUpdateDto.getEmail());
        if (departmentUpdateDto.getLocation() != null)
            department.setLocation(departmentUpdateDto.getLocation());
        return departmentMapper.toDepartmentResponseDto(departmentRepository.save(department));
    }

    @Override
    public CommonResponseDto deleteDepartment(String id) {
        departmentRepository.delete(departmentRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.Department.ERR_NOT_FOUND_ID, new String[]{id})));
        return new CommonResponseDto(true, CommonConstant.SUCCESS);
    }
}
