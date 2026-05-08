package com.project.Kdemy.controller;

import com.project.Kdemy.dto.CourseRequestDto;
import com.project.Kdemy.dto.CourseResponseDto;
import com.project.Kdemy.model.Course;
import com.project.Kdemy.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/courses")
@AllArgsConstructor
@Data
public class CourseController {

    private final CourseService courseService;
    @PostMapping("/create")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<CourseResponseDto> createCourse(@RequestBody CourseRequestDto req, Authentication authentication) {


        System.out.println(authentication.getName());
        System.out.println(authentication.getDetails());

        Course course = courseService.CreateCourse(req, authentication.getName());

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
    }

    @PutMapping("/{courseId}/publish")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<String> publishCourse(@PathVariable Long courseId) {
        courseService.publishCourse(courseId);
        return ResponseEntity.status(200).body("Course Published");
    }

}
