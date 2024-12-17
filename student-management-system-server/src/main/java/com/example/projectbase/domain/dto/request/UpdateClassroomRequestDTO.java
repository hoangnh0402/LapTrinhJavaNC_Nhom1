package com.example.projectbase.domain.dto.request;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateClassroomRequestDTO {

    @NotBlank(message = "Code is required")
    private String code;

    @Min(value = 1, message = "Number of students must be at least 1")
    private Integer numberOfStudents;

    @NotBlank(message = "Schedule is required")
    private String schedule;

    @NotBlank(message = "Room is required")
    private String room;

    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date must be today or in the future")
    private LocalDate startDate;

    @NotBlank(message = "Subject ID is required")
    private String subjectId;
}
