package com.example.projectbase.controller;

import com.example.projectbase.base.RestApiV1;
import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.dto.request.TeacherCreateDto;
import com.example.projectbase.domain.dto.request.UserCreateDto;
import com.example.projectbase.domain.dto.request.UserUpdateDto;
import com.example.projectbase.security.CurrentUser;
import com.example.projectbase.security.UserPrincipal;
import com.example.projectbase.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestApiV1
public class UserController {

  private final UserService userService;

  @Tag(name = "user-controller-admin")
  @Operation(summary = "API get user")
  @GetMapping(UrlConstant.User.GET_USER)
  public ResponseEntity<?> getUserById(@PathVariable String userId) {
    return VsResponseUtil.success(userService.getUserById(userId));
  }

  @Tag(name = "user-controller-admin")
  @Operation(summary = "API get user by user code")
  @GetMapping(UrlConstant.User.GET_USER_BY_USERCODE)
  public ResponseEntity<?> getUserByUserCode(@PathVariable String userCode) {
    return VsResponseUtil.success(userService.getUserByUserCode(userCode));
  }

  @Tags({@Tag(name = "user-controller-admin"), @Tag(name = "user-controller")})
  @Operation(summary = "API get current user login")
  @GetMapping(UrlConstant.User.GET_CURRENT_USER)
  public ResponseEntity<?> getCurrentUser(@Parameter(name = "principal", hidden = true)
                                          @CurrentUser UserPrincipal principal) {
    return VsResponseUtil.success(userService.getCurrentUser(principal));
  }

  @Tags({@Tag(name = "user-controller-admin")})
  @Operation(summary = "API create new student")
  @PostMapping(UrlConstant.User.CREATE_STUDENT)
  public ResponseEntity<?> createStudent(@RequestBody UserCreateDto userCreateDto) {
    return VsResponseUtil.success(userService.createStudent(userCreateDto));
  }

  @Tags({@Tag(name = "user-controller-admin")})
  @Operation(summary = "API create new teacher")
  @PostMapping(UrlConstant.User.CREATE_TEACHER)
  public ResponseEntity<?> createTeacher(@RequestBody TeacherCreateDto teacherCreateDto) {
    return VsResponseUtil.success(userService.createTeacher(teacherCreateDto));
  }

  @Tags({@Tag(name = "user-controller-admin"), @Tag(name = "user-controller")})
  @Operation(summary = "API get all student")
  @GetMapping(UrlConstant.User.GET_STUDENT)
  public ResponseEntity<?> getStudents() {
    return VsResponseUtil.success(userService.getAllStudents());
  }

  @Tags({@Tag(name = "user-controller-admin"), @Tag(name = "user-controller")})
  @Operation(summary = "API get all teacher")
  @GetMapping(UrlConstant.User.GET_TEACHER)
  public ResponseEntity<?> getTeachers() {
    return VsResponseUtil.success(userService.getAllTeachers());
  }

  @Tags({@Tag(name = "user-controller-admin"), @Tag(name = "user-controller")})
  @Operation(summary = "API update user")
  @PutMapping(UrlConstant.User.UPDATE_USER)
  public ResponseEntity<?> udpateUser(@RequestParam String userId, @RequestBody UserUpdateDto userUpdateDto) {
    return VsResponseUtil.success(userService.updateUser(userId, userUpdateDto));
  }

  @Tags({@Tag(name = "user-controller-admin"), @Tag(name = "user-controller")})
  @Operation(summary = "API lock user")
  @PatchMapping(UrlConstant.User.LOCK_USER)
  public ResponseEntity<?> lockUser(@RequestParam String userId) {
    return VsResponseUtil.success(userService.lockUser(userId));
  }

  @Tags({@Tag(name = "user-controller-admin")})
  @Operation(summary = "API unlock user")
  @PatchMapping(UrlConstant.User.UNLOCK_USER)
  public ResponseEntity<?> unlockUser(@RequestParam String userId) {
    return VsResponseUtil.success(userService.unlockUser(userId));
  }
}