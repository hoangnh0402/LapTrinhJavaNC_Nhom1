package com.example.projectbase.domain.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EnrollmentResponse {
    private String id;
    private double firstRegularPoint;
    private double secondRegularPoint;
    private double midTermPoint;
    private double finalPoint;
    private String userID;
    private String classroomID;
}

