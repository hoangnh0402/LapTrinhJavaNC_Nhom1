package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.RecordRequestDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.RecordResponseDto;

import java.util.List;

public interface RecordService {
    RecordResponseDto createRecord(RecordRequestDto recordRequestDto);
    RecordResponseDto updateRecord(RecordRequestDto recordRequestDto);
    List<RecordResponseDto> getAllRecords();
    RecordResponseDto getRecordsByUserCode(String userCode);
    CommonResponseDto deleteRecord(String id);
    long countStudentsByRatingAndClassName(String rating, String className);
    long countStudentsByRatingAndDepartmentName(String rating, String departmentName);
    List<RecordResponseDto> findAllByRatingAndClassName(String rating, String className);
    List<RecordResponseDto> findAllByRatingAndDepartmentName(String rating, String departmentName);
    long countStudentsByRating(String rating);
}
