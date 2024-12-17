package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.UpdateClassroomRequestDTO;
import com.example.projectbase.domain.dto.response.ClassroomResponseDTO;
import com.example.projectbase.domain.entity.Classroom;
import com.example.projectbase.domain.dto.request.CreateClassroomRequestDTO;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import java.time.LocalDate;
import java.util.List;

public interface ClassroomService {
    ClassroomResponseDTO createClassroom(CreateClassroomRequestDTO requestDTO);

    List<ClassroomResponseDTO> getAllClassrooms();

    List<Classroom> getAll();

    ClassroomResponseDTO getClassroomById(String id);

    ClassroomResponseDTO updateClassroom(String id, UpdateClassroomRequestDTO requestDTO);

    List<Classroom> findByStartDate(LocalDate startDate);

    List<Classroom> findAllClassroomBySubjectCode(String code);

    List<Classroom> findBySchedule(String schedule);

    CommonResponseDto deleteClassroom(String id);

    List<ClassroomResponseDTO> getAllClassroomsStudentCode(String studentCode);
}