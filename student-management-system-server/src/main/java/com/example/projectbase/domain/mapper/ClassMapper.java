package com.example.projectbase.domain.mapper;

import com.example.projectbase.domain.dto.request.ClassCreateDto;
import com.example.projectbase.domain.dto.response.ClassResponseDto;
import com.example.projectbase.domain.entity.Class;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClassMapper {
    Class toEntity(ClassCreateDto classCreateDto);
    ClassResponseDto toClassResponseDto(Class classEntity);
    List<ClassResponseDto> toClassResponseDtoList(List<Class> classEntityList);
}
