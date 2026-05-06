package com.project.Kdemy.controller;

import com.project.Kdemy.dto.CourseRequestDto;
<<<<<<< HEAD
import com.project.Kdemy.dto.CourseResponseDto;
=======
>>>>>>> c85368aab4ccea7364855d8cb229bc169ca3ef19
import com.project.Kdemy.model.Course;
import com.project.Kdemy.service.CourseService;
import com.project.Kdemy.service.ServiceImpl.CourseServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
<<<<<<< HEAD
import java.util.stream.Collectors;
=======
>>>>>>> c85368aab4ccea7364855d8cb229bc169ca3ef19

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
@Data

public class CourseController {

    private final CourseService courseService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('INSTRUCTOR')")
<<<<<<< HEAD
    public ResponseEntity<CourseResponseDto> createCourse(@RequestBody CourseRequestDto req, Authentication authentication) {
=======
    public ResponseEntity<Course> createCourse(@RequestBody CourseRequestDto req, Authentication authentication) {
>>>>>>> c85368aab4ccea7364855d8cb229bc169ca3ef19

        System.out.println(authentication.getName());
        System.out.println(authentication.getDetails());

        Course course = courseService.CreateCourse(req, authentication.getName());
<<<<<<< HEAD

        CourseResponseDto res = new CourseResponseDto(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getPrice(),
                course.getCategory().getName(),
                course.getInstructor().getUsername(),
                course.getCreatedAt()
        );
        return ResponseEntity.ok(res);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getAllPublishedCourse() {
        return ResponseEntity.ok(courseService.getAllCourses().stream()
                .map(course -> new CourseResponseDto(
                        course.getId(),
                        course.getTitle(),
                        course.getDescription(),
                        course.getPrice(),
                        course.getCategory().getName(),
                        course.getInstructor().getUsername(),
                        course.getCreatedAt()
                )).collect(Collectors.toList()));
=======
        return ResponseEntity.ok(course);
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllPublishedCourse() {
        return ResponseEntity.ok(courseService.getAllCourses());
>>>>>>> c85368aab4ccea7364855d8cb229bc169ca3ef19
    }

    @PutMapping("/{courseId}/publish")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<String> publishCourse(@PathVariable Long courseId) {
        courseService.publishCourse(courseId);
        return ResponseEntity.status(200).body("Course Published");
    }

}
