package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.MessageConstrant;
import com.example.projectbase.domain.dto.request.CreateClassroomRequestDTO;
import com.example.projectbase.domain.dto.request.UpdateClassroomRequestDTO;
import com.example.projectbase.domain.dto.response.ClassroomResponseDTO;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.entity.Classroom;
import com.example.projectbase.domain.entity.Subject;
import com.example.projectbase.domain.entity.User;
import com.example.projectbase.domain.mapper.ClassroomMapper;

import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.ClassroomRepository;
import com.example.projectbase.repository.UserRepository;
import com.example.projectbase.service.ClassroomService;
import com.example.projectbase.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final ClassroomMapper classroomMapper;
    private final SubjectService subjectService;
    private final UserRepository userRepository;

    @Override
    public ClassroomResponseDTO createClassroom(CreateClassroomRequestDTO requestDTO) {
        // Find the associated Subject
        Subject subject = subjectService.getSubjectById(requestDTO.getSubjectId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Subject.ERR_NOT_FOUND_ID, new String[]{requestDTO.getSubjectId()}));

        User teacher = userRepository.findById(requestDTO.getTeacherId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID, new String[]{requestDTO.getTeacherId()}));

        // Map DTO to Entity and set the Subject
        Classroom classroom = classroomMapper.toEntity(requestDTO, subject);
        classroom.setTeacher(teacher);

        // Save and map back to DTO
        return classroomMapper.toDto(classroomRepository.save(classroom));
    }


    @Override
    public List<ClassroomResponseDTO> getAllClassrooms() {
        List<Classroom> classrooms = classroomRepository.findAll();
        return classrooms.stream().map(classroomMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<Classroom> getAll() {
        return classroomRepository.findAll();
    }

    @Override
    public ClassroomResponseDTO getClassroomById(String id) {
        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Classroom.ERR_NOT_FOUND_ID, new String[]{id}));
        return classroomMapper.toDto(classroom);
    }

    @Override
    public ClassroomResponseDTO updateClassroom(String id, UpdateClassroomRequestDTO requestDTO) {
        Classroom existingClassroom = classroomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Classroom.ERR_NOT_FOUND_ID, new String[]{id}));

        // Find the associated Subject
        Subject subject = subjectService.getSubjectById(requestDTO.getSubjectId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Subject.ERR_NOT_FOUND_ID, new String[]{requestDTO.getSubjectId()}));

        // Update existing entity using mapper
        classroomMapper.updateEntity(requestDTO, existingClassroom, subject);

        // Save and map back to DTO
        return classroomMapper.toDto(classroomRepository.save(existingClassroom));
    }

    @Override
    public List<Classroom> findByStartDate(LocalDate startDate) {
        return classroomRepository.findByStartDate(startDate);
    }

    @Override
    public List<Classroom> findBySchedule(String schedule) {
        return classroomRepository.findBySchedule(schedule);
    }

    @Override
    public CommonResponseDto deleteClassroom(String id) {
        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Classroom.ERR_NOT_FOUND_ID, new String[]{id}));
        classroomRepository.deleteById(id);
        return new CommonResponseDto(true, MessageConstrant.SUCCESS);
    }

    @Override
    public List<ClassroomResponseDTO> getAllClassroomsStudentCode(String studentCode) {
        List<Classroom> classrooms = classroomRepository.findAllClassroomsByStudentCode(studentCode);
        return classroomMapper.toDto1(classrooms);  // Chuyển đổi Classroom thành ClassroomResponseDTO
    }

    @Override
    public List<Classroom> findAllClassroomBySubjectCode(String code) {
        return classroomRepository.findAllClassroomBySubjectCode(code);
    }
}
