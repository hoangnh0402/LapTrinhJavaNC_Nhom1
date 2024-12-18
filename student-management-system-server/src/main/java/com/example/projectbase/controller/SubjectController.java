package com.example.projectbase.controller;

import com.example.projectbase.base.RestApiV1;
import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.request.RequestCreateSubjectDTO;
import com.example.projectbase.domain.dto.request.UpdateRequestSubjectDTO;
import com.example.projectbase.service.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestApiV1
public class SubjectController {

    private final SubjectService subjectService;

    @Tags({@Tag(name = "subject-controller-admin")})
    @Operation(summary = "API create subject")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(UrlConstant.Subject.CREATE_SUBJECT)
    public ResponseEntity<?> createSubject(@RequestBody @Valid RequestCreateSubjectDTO subjectRequestDTO) {
        return VsResponseUtil.success(subjectService.createSubject(subjectRequestDTO));
    }

    @Tags({@Tag(name = "subject-controller")})
    @Operation(summary = "API get all subjects")
    @GetMapping(UrlConstant.Subject.GET_ALL_SUBJECTS)
    public ResponseEntity<?> readAllSubjects(@Valid @ParameterObject PaginationFullRequestDto paginationRequestDto) {
        return VsResponseUtil.success(subjectService.readAllSubjects(paginationRequestDto));
    }

    @Tags({@Tag(name = "subject-controller-admin")})
    @Operation(summary = "API get subjects with filtering and sorting")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(UrlConstant.Subject.GET_SUBJECTS)
    public ResponseEntity<?> readSubjects(@Valid @ParameterObject PaginationFullRequestDto paginationRequestDto) {
        return VsResponseUtil.success(subjectService.readSubjects(paginationRequestDto));
    }

    @Tags({@Tag(name = "subject-controller")})
    @Operation(summary = "API find subjects by name")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(UrlConstant.Subject.FIND_SUBJECTS)
    public ResponseEntity<?> findSubjects(@RequestParam String subjectName) {
        return VsResponseUtil.success(subjectService.findSubjects(subjectName));
    }

    @Tags({@Tag(name = "subject-controller-admin")})
    @Operation(summary = "API update subject")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(UrlConstant.Subject.UPDATE_SUBJECT)
    public ResponseEntity<?> updateSubject(@RequestParam String subjectId, @RequestBody @Valid UpdateRequestSubjectDTO subjectRequestDTO) {
        return VsResponseUtil.success(subjectService.updateSubject(subjectId, subjectRequestDTO));
    }

    @Tags({@Tag(name = "subject-controller-admin")})
    @Operation(summary = "API delete subject")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(UrlConstant.Subject.DELETE_SUBJECT)
    public ResponseEntity<?> deleteSubject(@RequestParam String subjectId) {
        return VsResponseUtil.success(subjectService.deleteSubject(subjectId));
    }

    @Tags({@Tag(name = "subject-controller-admin")})
    @Operation(summary = "API get subject by id")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'STUDENT')")
    @GetMapping(UrlConstant.Subject.GET_SUBJECT_BY_ID)
    public ResponseEntity<?> readById(@RequestParam String subjectId) throws Exception {
        return VsResponseUtil.success(subjectService.getSubjectById(subjectId));
    }

}
