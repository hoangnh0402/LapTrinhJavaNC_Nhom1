
package com.example.projectbase.domain.entity;

import com.example.projectbase.domain.entity.common.FlagUserDateAuditing;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "classrooms")
public class Classroom extends FlagUserDateAuditing {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(insertable = false, updatable = false, nullable = false, columnDefinition = "CHAR(36)")
    private String id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private int numberOfStudents;

    @Column(nullable = false)
    private String schedule;

    @Column(nullable = false)
    private String room;

    @Column(nullable = false)
    private LocalDate startDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "classroom")
    @JsonIgnore
    private Set<Enrollment> enrollments = new HashSet<>();

    //Link to table Subject
    @ManyToOne
    @JoinColumn(name = "subjectId", foreignKey = @ForeignKey(name = "FK_CLASSROOM_SUBJECT"))
    private Subject subject;

    //Link to table User(Teacher)
    @ManyToOne
    @JoinColumn(name = "teacherId", foreignKey = @ForeignKey(name = "FK_CLASSROOM_TEACHER"))
    private User teacher;
}
