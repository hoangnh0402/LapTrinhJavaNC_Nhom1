package com.example.projectbase.domain.mapper;

import com.example.projectbase.domain.dto.request.CreateClassroomRequestDTO;
import com.example.projectbase.domain.dto.request.UpdateClassroomRequestDTO;
import com.example.projectbase.domain.dto.response.ClassroomResponseDTO;
import com.example.projectbase.domain.entity.Classroom;
import com.example.projectbase.domain.entity.Enrollment;
import com.example.projectbase.domain.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ClassroomMapper {

    @Mapping(source = "createClassroomRequestDTO.code", target = "code")
    @Mapping(source = "createClassroomRequestDTO.numberOfStudents", target = "numberOfStudents")
    @Mapping(source = "createClassroomRequestDTO.schedule", target = "schedule")
    @Mapping(source = "createClassroomRequestDTO.room", target = "room")
    @Mapping(source = "createClassroomRequestDTO.startDate", target = "startDate")
    @Mapping(source = "subject", target = "subject")
    @Mapping(target = "enrollments", expression = "java(new java.util.HashSet<>())") // Khởi tạo enrollments
    Classroom toEntity(CreateClassroomRequestDTO createClassroomRequestDTO, Subject subject);


    @Mapping(source = "subject.id", target = "subjectId")
    @Mapping(target = "teacherId", source = "classroom.teacher.id")
    ClassroomResponseDTO toDto(Classroom classroom);

    default String getFirstUserIdFromEnrollments(Classroom classroom) {
        if (classroom.getEnrollments() == null || classroom.getEnrollments().isEmpty()) {
            return null;
        }
        return classroom.getEnrollments().stream()
                .filter(enrollment -> enrollment.getUser() != null)  // Bỏ qua Enrollment không có User
                .map(enrollment -> enrollment.getUser().getId())    // Lấy ID của User
                .findFirst()                                        // Lấy bản ghi đầu tiên
                .orElse(null);                                      // Trả về null nếu không tìm thấy
    }


    default List<ClassroomResponseDTO> toDto1(List<Classroom> classrooms) {
        return classrooms.stream()
                .map(this::toDto)  // Chuyển đổi từng Classroom thành ClassroomResponseDTO
                .collect(Collectors.toList());
    }


    // Cập nhật entity Classroom với thông tin từ UpdateClassroomRequestDTO
    @Mapping(source = "updateClassroomRequestDTO.code", target = "code")
    @Mapping(source = "updateClassroomRequestDTO.numberOfStudents", target = "numberOfStudents")
    @Mapping(source = "updateClassroomRequestDTO.schedule", target = "schedule")
    @Mapping(source = "updateClassroomRequestDTO.room", target = "room")
    @Mapping(source = "updateClassroomRequestDTO.startDate", target = "startDate")
    @Mapping(source = "subject", target = "subject")
    void updateEntity(UpdateClassroomRequestDTO updateClassroomRequestDTO, @MappingTarget Classroom classroom, Subject subject);
}
