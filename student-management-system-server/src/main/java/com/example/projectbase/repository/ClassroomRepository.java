package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, String> {

    // Tìm lớp học theo mã lớp học
    List<Classroom> findByCode(String code);

    @Query("SELECT c FROM Classroom c WHERE c.subject.code = :code")
    List<Classroom> findAllClassroomBySubjectCode(String code);

    // Tìm lớp học theo số lượng sinh viên
    List<Classroom> findByNumberOfStudents(int numberOfStudents);

    // Tìm lớp học theo ngày bắt đầu
    List<Classroom> findByStartDate(LocalDate startDate);

    // Tìm lớp học theo lịch học
    List<Classroom> findBySchedule(String schedule);

    @Query("SELECT c FROM Classroom c JOIN c.enrollments e JOIN e.user u WHERE u.username = :studentCode")
    List<Classroom> findAllClassroomsByStudentCode(@Param("studentCode") String studentCode);
}
