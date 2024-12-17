package com.example.projectbase.domain.dto.response;

import com.example.projectbase.domain.dto.common.DateAuditingDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto extends DateAuditingDto {

  private String id;

  private String username;

  private String email;

  private String phoneNumber;

  private String fullName;

  private String gender;

  private String birthday;

  private String address;

  private String avatar;

  private String classId;

  private String roleName;

}

