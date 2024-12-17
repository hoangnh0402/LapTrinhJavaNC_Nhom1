package com.example.projectbase.service;

import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.request.RequestCreateSubjectDTO;
import com.example.projectbase.domain.dto.request.UpdateRequestSubjectDTO;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.SubjectResponseDTO;
import com.example.projectbase.domain.entity.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectService {

    /// Tạo mới một Subject
    SubjectResponseDTO createSubject(RequestCreateSubjectDTO subjectRequestDTO);

    // Đọc tất cả các Subject với phân trang đầy đủ
    PaginationResponseDto<Subject> readAllSubjects(PaginationFullRequestDto paginationRequestDTO);

    // Đọc các Subject theo một điều kiện cụ thể
    PaginationResponseDto<Subject> readSubjects(PaginationFullRequestDto paginationRequestDTO);

    // Tìm kiếm Subject theo tên
    List<SubjectResponseDTO> findSubjects(String subjectName);

    // Cập nhật Subject
    SubjectResponseDTO updateSubject(String subjectId, UpdateRequestSubjectDTO subjectRequestDTO);

    // Xóa Subject theo ID
    CommonResponseDto deleteSubject(String subjectId);

    // Lấy danh sách tất cả các Subject
    List<Subject> getAllSubjects();

    Optional<Subject> getSubjectById(String id);
}