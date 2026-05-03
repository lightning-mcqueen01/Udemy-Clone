package com.project.Kdemy.repository;

import com.project.Kdemy.model.Course;
import com.project.Kdemy.model.Enrollment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByStudentEmailAndCourseId(String studentEmail, Long courseId);

    List<Enrollment> findByStudentEmail(String studentEmail);

    @Transactional
    void deleteByCourseId(Long courseId);
}
