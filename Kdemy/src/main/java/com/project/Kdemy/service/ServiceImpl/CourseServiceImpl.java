package com.project.Kdemy.service.ServiceImpl;

import com.project.Kdemy.dto.CourseRequestDto;
import com.project.Kdemy.model.Category;
import com.project.Kdemy.model.Course;
import com.project.Kdemy.model.CourseStatus;
import com.project.Kdemy.model.User;
import com.project.Kdemy.repository.CourseRepository;
import com.project.Kdemy.repository.UserRepository;
import com.project.Kdemy.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Data
public class CourseServiceImpl implements CourseService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Override
    public Course CreateCourse(CourseRequestDto req, String email) {

        User instructor = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("instructor not found"));

        Category category = courseRepository.findById(req.getCategoryId()).orElseThrow(() -> new RuntimeException("")).getCategory();

        Course course = new Course();
        course.setTitle(req.getTitle());
        course.setDescription(req.getDescription());
        course.setPrice(req.getPrice());
        course.setCategory(category);
        course.setInstructor(instructor);
        course.setStatus(CourseStatus.DRAFT);
        course.setCreatedAt(LocalDateTime.now());

        return courseRepository.save(course);
    }

    public @Nullable List<Course> getAllCourses() {

        return courseRepository.findByStatus(CourseStatus.PUBLISHED);
    }

    public void publishCourse(Long courseId) {

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("course not found"));

        course.setStatus(CourseStatus.PUBLISHED);

        courseRepository.save(course);

    }
}
