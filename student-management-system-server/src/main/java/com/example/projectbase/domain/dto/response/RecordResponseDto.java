package com.example.projectbase.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecordResponseDto {
    private int totalCredits;

    private int totalScore;

    private double GPA;

    private String rating;

    private String userCode;
}
