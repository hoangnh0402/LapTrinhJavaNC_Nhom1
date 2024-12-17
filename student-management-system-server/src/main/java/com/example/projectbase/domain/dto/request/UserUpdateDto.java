package com.example.projectbase.domain.dto.request;

import com.example.projectbase.constant.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserUpdateDto {

  @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
  private String userCode;

  @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
  private String email;

  @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
  private String phoneNumber;

  @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
  private String fullName;

  @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
  private String gender;

  @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
  private String birthday;

  @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
  private String address;

  private String avatar;

  @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
  private String classId;

}