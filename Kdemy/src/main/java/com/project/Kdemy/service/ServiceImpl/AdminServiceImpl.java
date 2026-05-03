package com.project.Kdemy.service.ServiceImpl;

import com.project.Kdemy.dto.UserResponseDto;
import com.project.Kdemy.exception.ResourceNotFoundException;
import com.project.Kdemy.model.Course;
import com.project.Kdemy.repository.CourseRepository;
import com.project.Kdemy.repository.EnrollmentRepository;
import com.project.Kdemy.repository.UserRepository;
import com.project.Kdemy.service.AdminService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public @Nullable List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPhone(),
                        user.getRole(),
                        user.getCreatedAt()
                )).toList();
    }

    @Override
    public @Nullable List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public void deleteCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));

        enrollmentRepository.deleteByCourseId(courseId);

        courseRepository.delete(course);
    }

    @Override
    public List<UserResponseDto> getUsersByRole(String role) {
        return userRepository.findByRole(role)
                .stream()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPhone(),
                        user.getRole(),
                        user.getCreatedAt()
                ))
                .toList();
    }
}
