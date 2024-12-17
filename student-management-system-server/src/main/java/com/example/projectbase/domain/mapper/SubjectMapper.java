package com.example.projectbase.domain.mapper;

import com.example.projectbase.domain.dto.request.RequestCreateSubjectDTO;
import com.example.projectbase.domain.dto.request.UpdateRequestSubjectDTO;
import com.example.projectbase.domain.dto.response.SubjectResponseDTO;
import com.example.projectbase.domain.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    // Chuyển đổi từ DTO tạo mới sang Entity
    Subject toEntity(RequestCreateSubjectDTO subjectRequestDto);

    // Chuyển đổi từ Entity sang DTO phản hồi
    SubjectResponseDTO toDto(Subject subject);


    // Cập nhật Entity từ DTO cập nhật
    void updateEntityFromDto(UpdateRequestSubjectDTO updateSubjectRequestDto, @MappingTarget Subject subject);
}