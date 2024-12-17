package com.example.projectbase.controller;

import com.example.projectbase.base.RestApiV1;
import com.example.projectbase.base.RestData;
import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.dto.request.UpdateClassroomRequestDTO;
import com.example.projectbase.domain.dto.response.ClassroomResponseDTO;
import com.example.projectbase.domain.entity.Classroom;
import com.example.projectbase.domain.dto.request.CreateClassroomRequestDTO;
import com.example.projectbase.service.ClassroomService;
import com.example.projectbase.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestApiV1
public class ClassroomController {

    private final ClassroomService classroomService;
    private final EnrollmentService enrollmentService;

    @Tags({@Tag(name = "classroom-controller-admin")})
    @Operation(summary = "API create classroom")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(UrlConstant.Classroom.CREATE_CLASSROOM)
    public ResponseEntity<?> createClassroom(@RequestBody @Valid CreateClassroomRequestDTO requestDTO) {
        return VsResponseUtil.success(classroomService.createClassroom(requestDTO));
    }

    @Tags({@Tag(name = "classroom-controller")})
    @Operation(summary = "API get all classrooms")
    @GetMapping(UrlConstant.Classroom.GET_ALL_CLASSROOMS)
    public ResponseEntity<?> getAllClassrooms() {
        List<ClassroomResponseDTO> classrooms = classroomService.getAllClassrooms();
        return VsResponseUtil.success(classrooms);
    }

    @Tags({@Tag(name = "classroom-controller-admin")})
    @Operation(summary = "API get classroom by id")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'STUDENT')")
    @GetMapping(UrlConstant.Classroom.GET_CLASSROOM_BY_ID)
    public ResponseEntity<?> getClassroomById(@RequestParam String classroomId) {
        return VsResponseUtil.success(classroomService.getClassroomById(classroomId));
    }

    @Tags({@Tag(name = "classroom-controller-admin")})
    @Operation(summary = "API update classroom")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(UrlConstant.Classroom.UPDATE_CLASSROOM)
    public ResponseEntity<?> updateClassroom(
            @RequestParam String classroomId,
            @RequestBody @Valid UpdateClassroomRequestDTO requestDTO) {
        return VsResponseUtil.success(classroomService.updateClassroom(classroomId, requestDTO));
    }

    @Tags({@Tag(name = "classroom-controller-admin")})
    @Operation(summary = "API delete classroom")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(UrlConstant.Classroom.DELETE_CLASSROOM)
    public ResponseEntity<?> deleteClassroom(@RequestParam String classroomId) {
        return VsResponseUtil.success(classroomService.deleteClassroom(classroomId));
    }

    @Tags({@Tag(name = "classroom-controller")})
    @Operation(summary = "API get classrooms by start date")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(UrlConstant.Classroom.GET_CLASSROOMS_BY_START_DATE)
    public ResponseEntity<?> findByStartDate(@RequestParam LocalDate startDate) {
        return VsResponseUtil.success(classroomService.findByStartDate(startDate));
    }

    @Tags({@Tag(name = "classroom-controller")})
    @Operation(summary = "API get classrooms by schedule")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(UrlConstant.Classroom.GET_CLASSROOMS_BY_SCHEDULE)
    public ResponseEntity<?> findBySchedule(@RequestParam String schedule) {
        return VsResponseUtil.success(classroomService.findBySchedule(schedule));
    }

    @Tags({@Tag(name = "classroom-controller")})
    @Operation(summary = "API get classrooms by subject code")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(UrlConstant.Classroom.GET_CLASSROOMS_BY_SUBJECT_CODE)
    public ResponseEntity<?> getClassroomsBySubjectCode(@RequestParam String subjectCode) {
        List<Classroom> classrooms = classroomService.findAllClassroomBySubjectCode(subjectCode);
        return VsResponseUtil.success(classrooms);
    }

    @Tags({@Tag(name = "classroom-controller-admin")})
    @Operation(summary = "API add student to classroom")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(UrlConstant.Classroom.ADD_STUDENT_TO_CLASSROOM)
    public ResponseEntity<?> addStudentToClassroom(@PathVariable String classroomId, @RequestBody String username) {
        return VsResponseUtil.success(enrollmentService.addStudentToClassroom(classroomId, username));
    }

    @Tags({@Tag(name = "classroom-controller-admin"), @Tag(name = "classroom-controller")})
    @Operation(summary = "API remove student from classroom")
    @DeleteMapping(UrlConstant.Classroom.REMOVE_STUDENT_FROM_CLASSROOM)
    public ResponseEntity<?> removeStudentFromClassroom(@PathVariable String classroomId, @PathVariable String userId) {
        return VsResponseUtil.success(enrollmentService.removeStudentFromClassroom(classroomId, userId));
    }

    @Tags({@Tag(name = "classroom-controller")})
    @Operation(summary = "API get classrooms by student code")
    @GetMapping("/api/v1/classrooms/byStudentCode/{studentCode}")
    public ResponseEntity<RestData<?>> getClassroomsByStudentCode(@PathVariable String studentCode) {
        List<ClassroomResponseDTO> classrooms = classroomService.getAllClassroomsStudentCode(studentCode);
        return VsResponseUtil.success(classrooms);
    }

}
