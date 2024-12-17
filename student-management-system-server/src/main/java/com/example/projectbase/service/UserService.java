package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.TeacherCreateDto;
import com.example.projectbase.domain.dto.request.UserCreateDto;
import com.example.projectbase.domain.dto.request.UserUpdateDto;
import com.example.projectbase.domain.dto.response.UserDto;
import com.example.projectbase.security.UserPrincipal;

import java.util.List;

public interface UserService {

  UserDto createStudent(UserCreateDto userCreateDto);

  UserDto createTeacher(TeacherCreateDto teacherCreateDto);

  List<UserDto> getAllStudents();

  List<UserDto> getAllTeachers();

  UserDto getUserById(String userId);

  UserDto getUserByUserCode(String userCode);

  UserDto getCurrentUser(UserPrincipal principal);

  UserDto updateUser(String id, UserUpdateDto userUpdateDto);

  UserDto lockUser(String id);

  UserDto unlockUser(String id);
}
