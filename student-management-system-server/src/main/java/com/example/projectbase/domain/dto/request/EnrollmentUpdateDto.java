package com.example.projectbase.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnrollmentUpdateDto {
    private String userId;
    private String classroomId;
    private double firstRegularPoint;
    private double secondRegularPoint;
    private double midTermPoint;
    private double finalPoint;
}
