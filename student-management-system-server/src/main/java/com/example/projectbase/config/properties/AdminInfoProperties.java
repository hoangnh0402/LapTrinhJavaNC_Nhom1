package com.example.projectbase.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("admin")
@Getter
@Setter
public class AdminInfoProperties {

  private String username;
  private String password;
  private String userCode;
  private String email;
  private String phoneNumber;
  private String fullName;
  private String gender;
  private String birthday;
  private String address;
  private String avatar;
  private Boolean isLocked;

}
