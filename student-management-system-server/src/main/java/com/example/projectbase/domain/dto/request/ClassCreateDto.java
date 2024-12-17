package com.example.projectbase.domain.dto.request;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClassCreateDto {
    private String name;
    private String classCode;
    private String academicYear;
    private String departmentId;
}
