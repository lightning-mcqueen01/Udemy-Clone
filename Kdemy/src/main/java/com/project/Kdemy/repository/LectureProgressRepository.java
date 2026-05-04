package com.project.Kdemy.repository;

import com.project.Kdemy.model.LectureProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LectureProgressRepository extends JpaRepository<LectureProgress, Long> {

    Optional<LectureProgress> findByStudentEmailAndLectureId(String studentEmail, Long lectureId);

    // Get all progress for a student in a specific course
    @Query("SELECT lp FROM LectureProgress lp " +
            "WHERE lp.studentEmail = :email " +
            "AND lp.lecture.section.course.id = :courseId")
    List<LectureProgress> findByStudentEmailAndCourseId(
            @Param("email") String studentEmail,
            @Param("courseId") Long courseId
    );

    // to get last watched lecture in a course
    @Query("SELECT lp FROM LectureProgress lp" +
            "WHERE lp.studentEmail = :email " +
            "AND lp.lecture.section.course.id = :courseId " +
            "ORDER BY lp.lastWatchedAt DESC")
    List<LectureProgress> findLastWatchedInCourse(
            @Param("email") String studentEmail,
            @Param("courseId") Long courseId
    );


    // count all the completed lectures in a course
    @Query("select count(lp) from LectureProgress lp " +
            "where lp.studentEmail = : email" +
            "and lp.lecture.section.courseId = :courseId" +
            "and lp.completed = :true")
    int countCompletedLecturesInCourse(@Param("email") String studentEmail,
                                       @Param("courseId") Long CourseId);

    // get all the course with progress
    @Query("SELECT DISTINCT lp.lecture.section.course.id FROM LectureProgress lp " +
            "WHERE lp.studentEmail = :email")
    List<Long> findAllCourseIdsWithProgress(@Param("email") String studentEmail);
}
