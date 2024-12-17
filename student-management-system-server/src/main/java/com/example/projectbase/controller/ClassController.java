package com.example.projectbase.controller;

import com.example.projectbase.base.RestApiV1;
import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.dto.request.ClassCreateDto;
import com.example.projectbase.service.ClassService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestApiV1
public class ClassController {
    private final ClassService classService;

    @Tags({@Tag(name = "class-controller-admin")})
    @Operation(summary = "API create class")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(UrlConstant.Class.CREATE_CLASS)
    public ResponseEntity<?> createClass(@RequestBody ClassCreateDto classCreateDto) {
        return VsResponseUtil.success(classService.createClass(classCreateDto));
    }

    @Tags({@Tag(name = "class-controller-admin")})
    @Operation(summary = "API get all class")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(UrlConstant.Class.GET_CLASSES)
    public ResponseEntity<?> getClasss() {
        return VsResponseUtil.success(classService.getAllClasses());
    }

    @Tags({@Tag(name = "class-controller-admin")})
    @Operation(summary = "API get class by id")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(UrlConstant.Class.GET_CLASS)
    public ResponseEntity<?> getClassById(@RequestParam String id) {
        return VsResponseUtil.success(classService.getClassById(id));
    }

    @Tags({@Tag(name = "class-controller-admin")})
    @Operation(summary = "API update class")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(UrlConstant.Class.UPDATE_CLASS)
    public ResponseEntity<?> updateClass(@RequestParam String id, @RequestBody ClassCreateDto classUpdateDto) {
        return VsResponseUtil.success(classService.updateClass(id, classUpdateDto));
    }

    @Tags({@Tag(name = "class-controller-admin")})
    @Operation(summary = "API delete class")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(UrlConstant.Class.DELETE_CLASS)
    public ResponseEntity<?> getClasss(@RequestParam String id) {
        return VsResponseUtil.success(classService.deleteClass(id));
    }


}
