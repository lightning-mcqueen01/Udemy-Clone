package com.project.Kdemy.repository;

import com.project.Kdemy.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByStudentEmailAndCourseId(String studentEmail, Long courseId);
}
