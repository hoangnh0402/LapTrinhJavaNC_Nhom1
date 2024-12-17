package com.example.projectbase.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartmentResponseDto {
    private String id;

    private String name;

    private String code;

    private String dean;

    private String description;

    private String phoneNumber;

    private String email;

    private String  location;
}
