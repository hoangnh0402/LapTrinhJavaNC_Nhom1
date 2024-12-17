package com.example.projectbase.domain.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectResponseDTO {
    private String id; // ID của Subject
    private String name; // Tên của Subject
    private String code; // Mã môn học
    private double numberOfCredits; // Số tín chỉ
    private double regularCoefficient; // Hệ số điểm chuyên cần
    private double midTermCoefficient; // Hệ số điểm giữa kỳ
    private double finalCoefficient; // Hệ số điểm cuối kỳ
    private String description; // Mô tả môn học
}