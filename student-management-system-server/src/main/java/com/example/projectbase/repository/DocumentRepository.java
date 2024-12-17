package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, String> {
    // Lấy danh sách tài liệu theo môn học
    List<Document> findAllBySubject_Id(String subjectId);
}
