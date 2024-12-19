package com.example.projectbase.controller;

import com.example.projectbase.base.RestApiV1;
import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.dto.request.DepartmentCreateDto;
import com.example.projectbase.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestApiV1
public class DepartmentController {
    private final DepartmentService departmentService;

    @Tags({@Tag(name = "department-controller-admin")})
    @Operation(summary = "API create department")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(UrlConstant.Department.CREATE_DEPARTMENT)
    public ResponseEntity<?> createDepartment(@RequestBody DepartmentCreateDto departmentCreateDto) {
        return VsResponseUtil.success(departmentService.createDepartment(departmentCreateDto));
    }

    @Tags({@Tag(name = "department-controller-admin")})
    @Operation(summary = "API get all department")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(UrlConstant.Department.GET_DEPARTMENTS)
    public ResponseEntity<?> getDepartments() {
        return VsResponseUtil.success(departmentService.getAllDepartments());
    }

    @Tags({@Tag(name = "department-controller-admin")})
    @Operation(summary = "API get department by id")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(UrlConstant.Department.GET_DEPARTMENT)
    public ResponseEntity<?> getDepartmentById(@RequestParam String id) {
        return VsResponseUtil.success(departmentService.findDepartmentById(id));
    }

    @Tags({@Tag(name = "department-controller-admin")})
    @Operation(summary = "API update department")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(UrlConstant.Department.UPDATE_DEPARTMENT)
    public ResponseEntity<?> updateDepartment(@RequestParam String id, @RequestBody DepartmentCreateDto departmentUpdateDto) {
        return VsResponseUtil.success(departmentService.updateDepartment(id, departmentUpdateDto));
    }

    @Tags({@Tag(name = "department-controller-admin")})
    @Operation(summary = "API delete department")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(UrlConstant.Department.DELETE_DEPARTMENT)
    public ResponseEntity<?> getDepartments(@RequestParam String id) {
        return VsResponseUtil.success(departmentService.deleteDepartment(id));
    }

}
