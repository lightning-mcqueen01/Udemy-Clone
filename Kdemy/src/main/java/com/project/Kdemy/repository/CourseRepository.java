package com.project.Kdemy.repository;

import com.project.Kdemy.model.Course;
import com.project.Kdemy.model.CourseStatus;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Nullable List<Course> findByStatus(CourseStatus status);

//    Page<Course> findByTitleContainingIgnoreCaseAndPublishedTrue(
//            String keyword, Pageable pageable);

}
