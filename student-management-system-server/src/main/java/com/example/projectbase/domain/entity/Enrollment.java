
package com.example.projectbase.domain.entity;

import com.example.projectbase.domain.entity.common.FlagUserDateAuditing;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "enrollments")
public class Enrollment extends FlagUserDateAuditing {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(insertable = false, updatable = false, nullable = false, columnDefinition = "CHAR(36)")
    private String id;

    @Column(nullable = false)
    private double firstRegularPoint;

    @Column(nullable = false)
    private double secondRegularPoint;

    @Column(nullable = false)
    private double midTermPoint;

    @Column(nullable = false)
    private double finalPoint;

    //Link to table User
    @ManyToOne
    @JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "FK_ENROLLMENT_USER"))
    private User user;

    //Link to table Classroom
    @ManyToOne
    @JoinColumn(name = "classroomId", foreignKey = @ForeignKey(name = "FK_ENROLLMENT_CLASSROOM"))
    private Classroom classroom;
}
