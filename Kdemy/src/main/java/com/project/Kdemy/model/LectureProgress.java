package com.project.Kdemy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class LectureProgress {

    @Id
    @GeneratedValue
    private Long id;

    private String studentEmail;

    @ManyToOne
    private Lecture lecture;

    private boolean completed;

    private int watchedSeconds;

    @UpdateTimestamp
    private LocalDateTime lastWatchedAt;
}
