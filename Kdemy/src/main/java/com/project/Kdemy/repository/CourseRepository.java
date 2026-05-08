package com.project.Kdemy.repository;

import com.project.Kdemy.model.Course;
import com.project.Kdemy.model.CourseStatus;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Nullable List<Course> findByStatus(CourseStatus status);


    @Query("SELECT c FROM Course c " +
            "WHERE (:query IS NULL OR " +
            "      LOWER(c.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "      LOWER(c.description) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "      LOWER(c.instructor.username) LIKE LOWER(CONCAT('%', :query, '%'))) " +
            "AND (:categoryId IS NULL OR c.category.id = :categoryId) " +
            "AND (:instructorId IS NULL OR c.instructor.id = :instructorId) " +
            "AND (:minPrice IS NULL OR c.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR c.price <= :maxPrice) " +
            "AND (:status IS NULL OR c.status = :status)")
    List<Course> searchCourses(
            @Param("query") String query,
            @Param("categoryId") Long categoryId,
            @Param("instructorId") Long instructorId,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("status") CourseStatus status
    );

    @Query("SELECT c FROM Course c " +
            "WHERE LOWER(c.title) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "AND c.status = 'PUBLISHED'")
    List<Course> searchByTitleOnly(@Param("query") String query);

    @Query("SELECT c FROM Course c " +
        "WHERE c.category.id = :courseId And c.status = 'PUBLISHED'"
    ) List<Course> findByCategoryId(@Param("courseId") Long courseId);

    @Query("SELECT c FROM Course c WHERE c.instructor.id = :instructorId")
    List<Course> findByInstructorId(@Param("instructorId") Long instructorId);

    @Query("SELECT c FROM Course c " +
            "LEFT JOIN Enrollment e ON e.course.id = c.id " +
            "WHERE c.status = 'PUBLISHED' "
//            "GROUP BY c.id " +
//            "ORDER BY COUNT(e.id) DESC"
    )
    List<Course> findTrendingCourses();

//    Page<Course> findByTitleContainingIgnoreCaseAndPublishedTrue(
//            String keyword, Pageable pageable);

}
