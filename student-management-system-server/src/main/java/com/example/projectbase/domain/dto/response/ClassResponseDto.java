package com.example.projectbase.domain.dto.response;

import com.example.projectbase.domain.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClassResponseDto {
    private String id;
    private String name;
    private String classCode;
    private String academicYear;
    private Department department;
}
