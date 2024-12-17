package com.example.projectbase.service.impl;

import com.example.projectbase.constant.CommonConstant;
import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.RoleConstant;
import com.example.projectbase.domain.dto.request.EnrollmentUpdateDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.EnrollmentResponse;
import com.example.projectbase.domain.dto.response.UserDto;
import com.example.projectbase.domain.entity.Classroom;
import com.example.projectbase.domain.entity.Enrollment;
import com.example.projectbase.domain.entity.User;
import com.example.projectbase.domain.mapper.EnrollmentMapper;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.*;
import com.example.projectbase.security.UserPrincipal;
import com.example.projectbase.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final ClassroomRepository classroomRepository;
    private final SubjectRepository subjectRepository;
    private final RecordRepository repository;
    private final UserRepository userRepository;
    private final EnrollmentMapper enrollmentMapper;


    @Override
    public Enrollment registerSubject(UserPrincipal principal, String classroomId) {
        User user = userRepository.getUser(principal);

        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Classroom.ERR_NOT_FOUND_ID));

        boolean alreadyEnrolled = enrollmentRepository.existsByUserAndClassroom(user, classroom);
        if (alreadyEnrolled) {
            throw new IllegalStateException(ErrorMessage.Enrollment.USER_ALREADY_IN_CLASSROOM);
        }

        Enrollment enrollment = Enrollment.builder()
                .user(user)
                .classroom(classroom)
                .firstRegularPoint(0)
                .secondRegularPoint(0)
                .midTermPoint(0)
                .finalPoint(0)
                .build();

        enrollmentRepository.save(enrollment);

        return enrollment;
    }

    public List<EnrollmentResponse> getAllStudentsInClassroom(String classroomId) {
        List<Enrollment> enrollments = enrollmentRepository.findAllByClassroomId(classroomId);

        return enrollmentMapper.toEnrollmentResponses(enrollments);
    }

    @Override
    public Enrollment addStudentToClassroom(String classroomId, String username) {
        User student = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_USERNAME));

        if (!student.getRole().getName().equals(RoleConstant.STUDENT)) {
            throw new IllegalStateException(ErrorMessage.Enrollment.USER_NOT_STUDENT);
        }

        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Classroom.ERR_NOT_FOUND_ID));

        boolean alreadyEnrolled = enrollmentRepository.existsByUserAndClassroom(student, classroom);
        if (alreadyEnrolled) {
            throw new IllegalStateException(ErrorMessage.Enrollment.USER_ALREADY_IN_CLASSROOM);
        }

        Enrollment enrollment = Enrollment.builder()
                .user(student)
                .classroom(classroom)
                .firstRegularPoint(0)
                .secondRegularPoint(0)
                .midTermPoint(0)
                .finalPoint(0)
                .build();

        enrollmentRepository.save(enrollment);

        return enrollment;
    }

    @Override
    public Enrollment updateStudentInClassroom(EnrollmentUpdateDto updateDto) {
        User student = userRepository.findById(updateDto.getUserId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID));

        if (!student.getRole().getName().equals(RoleConstant.STUDENT)) {
            throw new IllegalStateException(ErrorMessage.Enrollment.USER_NOT_STUDENT);
        }
        Classroom classroom = classroomRepository.findById(updateDto.getClassroomId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Classroom.ERR_NOT_FOUND_ID));
        Enrollment enrollment = enrollmentRepository.findByUserAndClassroom(student, classroom);
        enrollment.setFirstRegularPoint(updateDto.getFirstRegularPoint());
        enrollment.setSecondRegularPoint(updateDto.getSecondRegularPoint());
        enrollment.setMidTermPoint(updateDto.getMidTermPoint());
        enrollment.setFinalPoint(updateDto.getFinalPoint());
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public CommonResponseDto removeStudentFromClassroom(String classroomId, String userId) {
        enrollmentRepository.deleteByUserIdAndClassroomId(userId, classroomId);
        return new CommonResponseDto(true, CommonConstant.SUCCESS);
    }


    @Override
    public List<UserDto> getAllStudentsInClassroom1(String classroomId) {
        List<Enrollment> enrollments = enrollmentRepository.findAllByClassroomId(classroomId);

        // Mapping từ Enrollment sang StudentResponseDTO
        return enrollments.stream()
                .map(enrollment -> UserDto.builder()
                        .id(enrollment.getUser().getId())
                        .username(enrollment.getUser().getUsername())
                        .email(enrollment.getUser().getEmail())
                        .phoneNumber(enrollment.getUser().getPhoneNumber())
                        .fullName(enrollment.getUser().getFullName())
                        .gender(enrollment.getUser().getGender())
                        .birthday(enrollment.getUser().getBirthday())
                        .address(enrollment.getUser().getAddress())
                        .avatar(enrollment.getUser().getAvatar())
                        .classId(enrollment.getUser().getUserClass().getId())
                        .roleName(enrollment.getUser().getRole().getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public CommonResponseDto rejectSubject(UserPrincipal principal, String classroomId) {
        // Kiểm tra quyền của người dùng
        String userId = principal.getId();
        Enrollment enrollment = enrollmentRepository.findByUserIdAndClassroomId(userId, classroomId)
                .orElseThrow(() -> new NotFoundException("Enrollment not found"));

        // Xóa bản ghi trong bảng enrollment
        enrollmentRepository.delete(enrollment);

        // Trả về phản hồi
        return new CommonResponseDto(true, CommonConstant.SUCCESS);
    }
}
