package com.example.projectbase.service.impl;

import com.example.projectbase.constant.CommonConstant;
import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.domain.dto.request.ClassCreateDto;
import com.example.projectbase.domain.dto.response.ClassResponseDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.entity.Class;
import com.example.projectbase.domain.mapper.ClassMapper;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.ClassRepository;
import com.example.projectbase.repository.DepartmentRepository;
import com.example.projectbase.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {
    private final ClassRepository classRepository;
    private final DepartmentRepository departmentRepository;
    private final ClassMapper classMapper;

    @Override
    public ClassResponseDto createClass(ClassCreateDto classCreateDto) {
        Class aClass = classMapper.toEntity(classCreateDto);
        aClass.setDepartment(departmentRepository.findById(classCreateDto.getDepartmentId()).orElseThrow(() -> new NotFoundException(ErrorMessage.Department.ERR_NOT_FOUND_ID, new String[]{classCreateDto.getDepartmentId()})));
        return classMapper.toClassResponseDto(classRepository.save(aClass));
    }

    @Override
    public List<ClassResponseDto> getAllClasses() {
        return classMapper.toClassResponseDtoList(classRepository.findAll());
    }

    @Override
    public ClassResponseDto getClassById(String id) {
        return classMapper.toClassResponseDto(classRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.Class.ERR_NOT_FOUND_ID, new String[]{id})));
    }

    @Override
    public ClassResponseDto updateClass(String id, ClassCreateDto classUpdateDto) {
        Class aClass =classRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.Class.ERR_NOT_FOUND_ID, new String[]{id}));
        if(classUpdateDto.getName() !=null)
            aClass.setName(classUpdateDto.getName());
        if (classUpdateDto.getClassCode() != null)
            aClass.setClassCode(classUpdateDto.getClassCode());
            aClass.setAcademicYear(classUpdateDto.getAcademicYear());
        if (classUpdateDto.getDepartmentId() != null){
            aClass.setDepartment(departmentRepository.findById(classUpdateDto.getDepartmentId()).orElseThrow(() -> new NotFoundException(ErrorMessage.Department.ERR_NOT_FOUND_ID, new String[]{classUpdateDto.getDepartmentId()})));
        }
        return classMapper.toClassResponseDto(classRepository.save(aClass));
    }

    @Override
    public CommonResponseDto deleteClass(String id) {
        classRepository.delete(classRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.Class.ERR_NOT_FOUND_ID, new String[]{id})));
        return new CommonResponseDto(true, CommonConstant.SUCCESS);
    }
}
