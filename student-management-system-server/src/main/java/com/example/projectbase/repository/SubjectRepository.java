package com.example.projectbase.repository;

import com.example.projectbase.domain.dto.response.SubjectResponseDTO;
import com.example.projectbase.domain.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {
    // Lấy môn học theo mã môn học
    Subject getSubjectByCode(String code);

    Subject findSubjectById(String id);

    // Lấy môn học theo tên
    List<Subject> findAllByNameContainingIgnoreCase(String name);
}