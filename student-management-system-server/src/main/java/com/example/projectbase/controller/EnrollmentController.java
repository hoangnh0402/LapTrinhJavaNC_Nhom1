package com.example.projectbase.controller;

import com.example.projectbase.base.RestApiV1;
import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.dto.request.EnrollmentUpdateDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.EnrollmentResponse;
import com.example.projectbase.domain.dto.response.UserDto;
import com.example.projectbase.domain.entity.Enrollment;
import com.example.projectbase.security.CurrentUser;
import com.example.projectbase.security.UserPrincipal;
import com.example.projectbase.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestApiV1
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @Tags({@Tag(name = "enrollment-controller-user")})
    @PostMapping(UrlConstant.Enrollment.REGISTER_SUBJECT)
    public ResponseEntity<?> registerSubject(@Parameter(name = "principal", hidden = true)
                                             @CurrentUser UserPrincipal principal,
                                             @RequestParam String classroomId) {
        Enrollment enrollment = enrollmentService.registerSubject(principal, classroomId);
        return VsResponseUtil.success(enrollment);
    }

    @Tags({@Tag(name = "enrollment-controller-admin"), @Tag(name = "enrollment-controller-teacher")})
    @Operation(summary = "API update date score for student")
    @PutMapping(UrlConstant.Enrollment.UPDATE_ENROLLMENT)
    public ResponseEntity<?> updateEnrollment(@RequestBody EnrollmentUpdateDto updateDto) {
        return VsResponseUtil.success(enrollmentService.updateStudentInClassroom(updateDto));
    }

    @Tags({@Tag(name = "enrollment-controller-admin"), @Tag(name = "enrollment-controller-teacher")})
    @Operation(summary = "API get all students in a classroom")
    @GetMapping("/{classroomId}/students")
    public ResponseEntity<Map<String, Object>> getAllStudentsInClassroom(@PathVariable String classroomId) {
        List<EnrollmentResponse> students = enrollmentService.getAllStudentsInClassroom(classroomId);
        Map<String, Object> response = new HashMap<>();
        response.put("data", students);
        return ResponseEntity.ok(response);
    }

    @Tags({@Tag(name = "enrollment-controller-admin"), @Tag(name = "enrollment-controller-teacher")})
    @Operation(summary = "API get all students in a classroom")
    @GetMapping("/{classroomId}/students1")
    public ResponseEntity<Map<String, Object>> getAllStudentsInClassroom1(@PathVariable String classroomId) {
        List<UserDto> students = enrollmentService.getAllStudentsInClassroom1(classroomId);
        Map<String, Object> response = new HashMap<>();
        response.put("data", students);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/reject/{classroomId}")
    @Tags({
            @Tag(name = "enrollment-controller-admin"),
            @Tag(name = "enrollment-controller-user")
    })
    @Operation(summary = "API để từ chối hoặc hủy đăng ký một lớp học")
    public ResponseEntity<CommonResponseDto> rejectSubject(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable String classroomId) {

        // Gọi service để xử lý logic
        CommonResponseDto response = enrollmentService.rejectSubject(principal, classroomId);

        // Trả về phản hồi HTTP
        return ResponseEntity.ok(response);
    }



}