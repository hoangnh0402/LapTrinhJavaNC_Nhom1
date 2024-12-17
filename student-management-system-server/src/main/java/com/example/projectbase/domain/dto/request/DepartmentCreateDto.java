package com.example.projectbase.domain.dto.request;

import com.example.projectbase.constant.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartmentCreateDto {
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String name;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String code;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String dean;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String description;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String phoneNumber;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String email;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String  location;
}
