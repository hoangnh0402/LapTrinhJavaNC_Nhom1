package com.example.projectbase.domain.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassroomResponseDTO {

    private String id;

    private String code;

    private String subjectId;

    private int numberOfStudents;

    private String teacherId;

    private String schedule;

    private String room;

    private LocalDate startDate;
}

