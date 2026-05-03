package com.project.Kdemy.service;

import com.project.Kdemy.dto.UserResponseDto;
import com.project.Kdemy.model.Course;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface AdminService {
    @Nullable List<UserResponseDto> getAllUsers();

    @Nullable List<Course> getAllCourses();

    void deleteCourse(Long courseId);

    @Nullable List<UserResponseDto> getUsersByRole(String role);
}
