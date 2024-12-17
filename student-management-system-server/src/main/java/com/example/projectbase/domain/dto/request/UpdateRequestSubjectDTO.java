package com.example.projectbase.domain.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateRequestSubjectDTO {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Code is required")
    private String code;

    @NotNull(message = "Number of credits is required")
    private Double numberOfCredits;

    @NotNull(message = "Regular coefficient is required")
    private Double regularCoefficient;

    @NotNull
    private double midTermCoefficient;

    @NotNull(message = "Final coefficient is required")
    private Double finalCoefficient;

    private String description;

    private String prerequisiteSubject;
}