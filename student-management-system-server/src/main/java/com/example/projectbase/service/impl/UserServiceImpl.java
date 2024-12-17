package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.RoleConstant;
import com.example.projectbase.domain.dto.request.TeacherCreateDto;
import com.example.projectbase.domain.dto.request.UserCreateDto;
import com.example.projectbase.domain.dto.request.UserUpdateDto;
import com.example.projectbase.domain.dto.response.UserDto;
import com.example.projectbase.domain.entity.User;
import com.example.projectbase.domain.mapper.UserMapper;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.ClassRepository;
import com.example.projectbase.repository.RoleRepository;
import com.example.projectbase.repository.UserRepository;
import com.example.projectbase.security.UserPrincipal;
import com.example.projectbase.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  private final PasswordEncoder passwordEncoder;

  private final RoleRepository roleRepository;

  private final ClassRepository classRepository;

  @Override
  public UserDto createStudent(UserCreateDto userCreateDto) {
    boolean userExists = userRepository.findByUsername(userCreateDto.getUsername()).isPresent();
    if (userExists) {
      throw new RuntimeException("Username '" + userCreateDto.getUsername() + "' already exists.");
    }
    User user = userMapper.toUser(userCreateDto);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setIsLocked(false);
    user.setRole(roleRepository.findByRoleName(RoleConstant.STUDENT));
    if (userCreateDto.getClassId() != null)
      user.setUserClass(
              classRepository.findById(userCreateDto.getClassId())
                      .orElseThrow(() -> new NotFoundException(ErrorMessage.Class.ERR_NOT_FOUND_ID, new String[]{userCreateDto.getClassId()}))
      );
    userRepository.save(user);
    return userMapper.toUserDto(user);
  }

  @Override
  public UserDto createTeacher(TeacherCreateDto teacherCreateDto) {
    boolean userExists = userRepository.findByUsername(teacherCreateDto.getUsername()).isPresent();
    if (userExists) {
      throw new RuntimeException("Username '" + teacherCreateDto.getUsername() + "' already exists.");
    }
    User user = userMapper.toUser(teacherCreateDto);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setIsLocked(false);
    user.setRole(roleRepository.findByRoleName(RoleConstant.TEACHER));
    System.out.println(user);
    userRepository.save(user);
    return userMapper.toUserDto(user);
  }

  @Override
  public List<UserDto> getAllStudents() {
    return userMapper.toUserDtos(
            userRepository.findAllByRoleNameAndIsLocked(RoleConstant.STUDENT, false)
    );
  }

  @Override
  public List<UserDto> getAllTeachers() {
    return userMapper.toUserDtos(
            userRepository.findAllByRoleNameAndIsLocked(RoleConstant.TEACHER, false)
    );
  }

  @Override
  public UserDto getUserById(String userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID, new String[]{userId}));
    return userMapper.toUserDto(user);
  }

  @Override
  public UserDto getUserByUserCode(String userCode) {
    User user = userRepository.findByUsername(userCode)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_USERNAME, new String[]{userCode}));
    return userMapper.toUserDto(user);
  }

  @Override
  public UserDto getCurrentUser(UserPrincipal principal) {
    User user = userRepository.getUser(principal);
    return userMapper.toUserDto(user);
  }

  @Override
  public UserDto updateUser(String id, UserUpdateDto userUpdateDto) {
    User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID, new String[]{id}));
    if(userUpdateDto.getUserCode()!= null)
      user.setUserCode(userUpdateDto.getUserCode());
    if(userUpdateDto.getEmail()!= null)
      user.setEmail(userUpdateDto.getEmail());
    if (userUpdateDto.getPhoneNumber() != null)
      user.setPhoneNumber(userUpdateDto.getPhoneNumber());
    if (userUpdateDto.getFullName() != null)
      user.setFullName(userUpdateDto.getFullName());
    if (userUpdateDto.getGender() != null)
      user.setGender(userUpdateDto.getGender());
    if (userUpdateDto.getBirthday() != null)
      user.setBirthday(userUpdateDto.getBirthday());
    if (userUpdateDto.getAddress() != null)
      user.setAddress(userUpdateDto.getAddress());
    if (userUpdateDto.getAvatar() != null)
      user.setAvatar(userUpdateDto.getAvatar());
    if (userUpdateDto.getClassId() != null)
      user.setUserClass(
              classRepository.findById(userUpdateDto.getClassId())
                      .orElseThrow(() -> new NotFoundException(ErrorMessage.Class.ERR_NOT_FOUND_ID, new String[]{userUpdateDto.getClassId()}))
      );
    return userMapper.toUserDto(userRepository.save(user));
  }

  @Override
  public UserDto lockUser(String id) {
    User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID, new String[]{id}));
    user.setIsLocked(true);
    return userMapper.toUserDto(userRepository.save(user));
  }

  @Override
  public UserDto unlockUser(String id) {
    User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID, new String[]{id}));
    user.setIsLocked(false);
    return userMapper.toUserDto(userRepository.save(user));
  }

}
