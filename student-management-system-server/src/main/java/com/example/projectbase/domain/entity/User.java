
package com.example.projectbase.domain.entity;

import com.example.projectbase.domain.entity.common.DateAuditing;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Nationalized;

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
@Table(name = "users")
public class User extends DateAuditing {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(insertable = false, updatable = false, nullable = false, columnDefinition = "CHAR(36)")
  private String id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  @JsonIgnore
  private String password;

  @Column
  private String userCode;

  @Column
  private String email;

  @Column
  private String phoneNumber;

  @Nationalized
  @Column
  private String fullName;

  @Nationalized
  @Column
  private String gender;

  @Column
  private String birthday;

  @Nationalized
  @Column
  private String address;

  private String avatar;

  @Column
  private Boolean isLocked;

  //Link to table Role
  @ManyToOne
  @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "FK_USER_ROLE"))
  private Role role;

  //Link to table Class
  @ManyToOne
  @JoinColumn(name = "class_id", foreignKey = @ForeignKey(name = "FK_USER_CLASS"))
  private Class userClass;

  //Link to table Enrollment
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  @JsonIgnore
  private Set<Enrollment> enrollments = new HashSet<>();

  //Link to table Record
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  @JsonIgnore
  private Set<Record> records = new HashSet<>();

  //Link to table Classroom
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
  @JsonIgnore
  private Set<Classroom> classrooms = new HashSet<>();
}
