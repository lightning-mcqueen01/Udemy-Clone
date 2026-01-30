package com.project.Kdemy.repository;

import com.project.Kdemy.model.LectureProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectureProgressRepository extends JpaRepository<LectureProgress, Long> {

    Optional<LectureProgress> findByStudentEmailAndLectureId(String studentEmail, Long lectureId);
}
