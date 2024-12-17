package com.example.projectbase.domain.mapper;

import com.example.projectbase.domain.dto.response.EnrollmentResponse;
import com.example.projectbase.domain.entity.Classroom;
import com.example.projectbase.domain.entity.Enrollment;
import com.example.projectbase.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    @Mappings({
            @Mapping(target = "userID", source = "user.id"),
            @Mapping(target = "classroomID", source = "classroom.id")
    })
    EnrollmentResponse toEnrollmentResponse(Enrollment enrollment);

    @Mappings({
            @Mapping(target = "userID", source = "user.id"),
            @Mapping(target = "classroomID", source = "classroom.id")
    })
    List<EnrollmentResponse> toEnrollmentResponses(List<Enrollment> enrollments);


}

