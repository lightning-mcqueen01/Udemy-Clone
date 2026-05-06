package com.project.Kdemy.service;

import com.project.Kdemy.dto.CourseRequestDto;
import com.project.Kdemy.model.Course;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface CourseService {

    public Course CreateCourse(CourseRequestDto req, String email);

    public @Nullable List<Course> getAllCourses();

    public void publishCourse(Long courseId);
}
