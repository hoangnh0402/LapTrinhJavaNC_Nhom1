package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.ClassCreateDto;
import com.example.projectbase.domain.dto.response.ClassResponseDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;

import java.util.List;

public interface ClassService {
    ClassResponseDto createClass(ClassCreateDto classCreateDto);
    List<ClassResponseDto> getAllClasses();
    ClassResponseDto getClassById(String id);
    ClassResponseDto updateClass(String id, ClassCreateDto classUpdateDto);
    CommonResponseDto deleteClass(String id);
}
