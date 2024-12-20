package com.example.projectbase.domain.dto.request;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecordRequestDto {
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private int totalCredits;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private int totalScore;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String userCode;
}
