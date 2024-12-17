package com.example.projectbase.domain.entity;

import com.example.projectbase.domain.entity.common.FlagUserDateAuditing;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "documents")
public class Document extends FlagUserDateAuditing {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(insertable = false, updatable = false, nullable = false, columnDefinition = "CHAR(36)")
    private String id;

    @Nationalized
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String description;

    //Link to table Subject
    @ManyToOne
    @JoinColumn(name = "subjectId", foreignKey = @ForeignKey(name = "FK_DOCUMENT_SUBJECT"))
    private Subject subject;
 }