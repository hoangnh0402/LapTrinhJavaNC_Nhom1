package com.example.projectbase.service;


import com.example.projectbase.domain.dto.request.EnrollmentUpdateDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.EnrollmentResponse;
import com.example.projectbase.domain.dto.response.UserDto;
import com.example.projectbase.domain.entity.Enrollment;
import com.example.projectbase.security.UserPrincipal;

import java.util.List;

public interface EnrollmentService {
    Enrollment registerSubject(UserPrincipal principal, String classroomId);
    public List<EnrollmentResponse> getAllStudentsInClassroom(String classroomId);
    public Enrollment addStudentToClassroom(String classroomId, String username);
    Enrollment updateStudentInClassroom(EnrollmentUpdateDto updateDto);
    public CommonResponseDto removeStudentFromClassroom(String classroomId, String username);
    public List<UserDto> getAllStudentsInClassroom1(String classroomId);
    CommonResponseDto rejectSubject(UserPrincipal principal, String classroomId);
}
