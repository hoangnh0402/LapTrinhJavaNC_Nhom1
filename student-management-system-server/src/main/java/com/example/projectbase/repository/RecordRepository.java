package com.example.projectbase.repository;
import com.example.projectbase.domain.entity.Record;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, String> {
    Record findAllByUserUsername(String userName);

    // Đếm số sinh viên đạt rating theo tên lớp
    @Query("SELECT COUNT(r) FROM Record r " +
            "JOIN r.user u " +
            "JOIN u.userClass c " +
            "WHERE r.rating = :rating AND c.name = :className")
    long countStudentsByRatingAndClassName(@Param("rating") String rating, @Param("className") String className);

    // Đếm số sinh viên đạt rating theo tên khoa
    @Query("SELECT COUNT(r) FROM Record r " +
            "JOIN r.user u " +
            "JOIN u.userClass c " +
            "JOIN c.department d " +
            "WHERE r.rating = :rating AND d.name = :departmentName")
    long countStudentsByRatingAndDepartmentName(@Param("rating") String rating, @Param("departmentName") String departmentName);

    // Lấy danh sách tất cả các sinh viên theo rating trong một lớp
    @Query("SELECT r FROM Record r " +
            "JOIN r.user u " +
            "JOIN u.userClass c " +
            "WHERE r.rating = :rating AND c.name = :className")
    List<Record> findAllByRatingAndClassName(@Param("rating") String rating, @Param("className") String className);

    // Lấy danh sách tất cả các sinh viên theo rating trong một khoa
    @Query("SELECT r FROM Record r " +
            "JOIN r.user u " +
            "JOIN u.userClass c " +
            "JOIN c.department d " +
            "WHERE r.rating = :rating AND d.name = :departmentName")
    List<Record> findAllByRatingAndDepartmentName(@Param("rating") String rating, @Param("departmentName") String departmentName);

    // Đếm tổng số sinh viên đạt rating không phân biệt lớp, khoa
    @Query("SELECT COUNT(r) FROM Record r WHERE r.rating = :rating")
    long countStudentsByRating(@Param("rating") String rating);
}
