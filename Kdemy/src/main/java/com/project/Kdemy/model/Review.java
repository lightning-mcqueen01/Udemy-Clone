package com.project.Kdemy.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_email", "course_id"})
})
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    private String studentEmail;

    private int rating;

    private String comment;

    @ManyToOne
    private Course course;

    private LocalDateTime createdAt;
}
