package com.example.projectbase.controller;

import com.example.projectbase.base.RestApiV1;
import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.dto.request.RecordRequestDto;
import com.example.projectbase.service.RecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestApiV1
public class RecordController {
    private final RecordService recordService;

    @Tags({@Tag(name = "record-controller-admin")})
    @Operation(summary = "API create record")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(UrlConstant.Record.CREATE_RECORD)
    public ResponseEntity<?> createRecord(@RequestBody RecordRequestDto recordCreateDto) {
        return VsResponseUtil.success(recordService.createRecord(recordCreateDto));
    }

    @Tags({@Tag(name = "record-controller-admin")})
    @Operation(summary = "API update record")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PatchMapping(UrlConstant.Record.UPDATE_RECORD)
    public ResponseEntity<?> updateRecord(@RequestBody RecordRequestDto recordCreateDto) {
        return VsResponseUtil.success(recordService.updateRecord(recordCreateDto));
    }

    @Tags({@Tag(name = "record-controller-admin")})
    @Operation(summary = "API get all record")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(UrlConstant.Record.GET_ALL_RECORD)
    public ResponseEntity<?> getRecords() {
        return VsResponseUtil.success(recordService.getAllRecords());
    }

    @Tags({@Tag(name = "record-controller-admin"), @Tag(name = "record-controller")})
    @Operation(summary = "API get record by userCode")
    @GetMapping(UrlConstant.Record.GET_RECORD_BY_USERCODE)
    public ResponseEntity<?> getRecordByUserCode(@RequestParam String userCode) {
        return VsResponseUtil.success(recordService.getRecordsByUserCode(userCode));
    }

    @Tags({@Tag(name = "record-controller-admin")})
    @Operation(summary = "API delete record")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(UrlConstant.Record.DELETE_RECORD)
    public ResponseEntity<?> deleteRecord(@RequestParam String id) {
        return VsResponseUtil.success(recordService.deleteRecord(id));
    }

    @Tags({@Tag(name = "record-controller-admin")})
    @Operation(summary = "API count student by rating")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(UrlConstant.Record.COUNT_BY_RATING)
    public ResponseEntity<?> countByRating(@RequestParam String rating) {
        return VsResponseUtil.success(recordService.countStudentsByRating(rating));
    }

    @Tags({@Tag(name = "record-controller-admin")})
    @Operation(summary = "API count student by rating in class")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(UrlConstant.Record.COUNT_BY_RATING_AND_CLASS)
    public ResponseEntity<?> countByRatingAndClass(@RequestParam String rating, @RequestParam String className) {
        return VsResponseUtil.success(recordService.countStudentsByRatingAndClassName(rating,className));
    }

    @Tags({@Tag(name = "record-controller-admin")})
    @Operation(summary = "API count student by rating in department")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(UrlConstant.Record.COUNT_BY_RATING_AND_DEPARTMENT)
    public ResponseEntity<?> countByRatingAndDepartment(@RequestParam String rating, @RequestParam String departmentName) {
        return VsResponseUtil.success(recordService.countStudentsByRatingAndDepartmentName(rating,departmentName));
    }

    @Tags({@Tag(name = "record-controller-admin")})
    @Operation(summary = "API get student by rating in class")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(UrlConstant.Record.GET_BY_RATING_AND_CLASS)
    public ResponseEntity<?> getByRatingAndClass(@RequestParam String rating, @RequestParam String className) {
        return VsResponseUtil.success(recordService.findAllByRatingAndClassName(rating,className));
    }

    @Tags({@Tag(name = "record-controller-admin")})
    @Operation(summary = "API get student by rating in department")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(UrlConstant.Record.GET_BY_RATING_AND_DEPARTMENT)
    public ResponseEntity<?> getByRatingAndDepartment(@RequestParam String rating, @RequestParam String departmentName) {
        return VsResponseUtil.success(recordService.findAllByRatingAndDepartmentName(rating,departmentName));
    }
}
