package com.example.projectbase.domain.entity;

import com.example.projectbase.domain.entity.common.FlagUserDateAuditing;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "classes")
public class Class extends FlagUserDateAuditing {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(insertable = false, updatable = false, nullable = false, columnDefinition = "CHAR(36)")
    private String id;

    @Nationalized
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String classCode;

    @Column(nullable = false)
    private String academicYear;

    //Link to table User
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userClass")
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    //Link to table Department
    @ManyToOne
    @JoinColumn(name = "departmentId", foreignKey = @ForeignKey(name = "FK_CLASS_DEPARTMENT"))
    private Department department;
}