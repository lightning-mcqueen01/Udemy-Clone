package com.project.Kdemy.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_email", "course_id"})
})
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String studentEmail;

    @ManyToOne
    private Course course;

    private LocalDateTime enrolledAt;

}
