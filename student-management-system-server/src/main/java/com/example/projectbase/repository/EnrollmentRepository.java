package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Classroom;
import com.example.projectbase.domain.entity.Enrollment;
import com.example.projectbase.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {
    boolean existsByUserAndClassroom(User user, Classroom classroom);
    List<Enrollment> findAllByClassroomId(String classroomId);

    @Query("SELECT e FROM Enrollment e WHERE e.user.id = :userId AND e.classroom.id = :classroomId")
    Optional<Enrollment> findByUserIdAndClassroomId(@Param("userId") String userId, @Param("classroomId") String classroomId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Enrollment e WHERE e.user.id = :userId AND e.classroom.id = :classroomId")
    void deleteByUserIdAndClassroomId(@Param("userId") String userId, @Param("classroomId") String classroomId);



    Enrollment findByUserAndClassroom(User user, Classroom classroom);
}
