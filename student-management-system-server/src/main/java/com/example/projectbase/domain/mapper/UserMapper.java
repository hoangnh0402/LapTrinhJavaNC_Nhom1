package com.example.projectbase.domain.mapper;

import com.example.projectbase.domain.dto.request.TeacherCreateDto;
import com.example.projectbase.domain.dto.request.UserCreateDto;
import com.example.projectbase.domain.dto.response.UserDto;
import com.example.projectbase.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateDto userCreateDto);
    User toUser(TeacherCreateDto teacherCreateDto);
    @Mappings({
            @Mapping(target = "roleName", source = "user.role.name"),
            @Mapping(target = "classId", source = "user.userClass.id")
    })
    UserDto toUserDto(User user);
    @Mappings({
            @Mapping(target = "roleName", source = "user.role.name"),
            @Mapping(target = "classId", source = "user.userClass.id")
    })
    List<UserDto> toUserDtos(List<User> users);
}
