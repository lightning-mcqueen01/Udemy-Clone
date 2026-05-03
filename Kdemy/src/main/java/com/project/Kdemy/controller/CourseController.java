package com.project.Kdemy.controller;

import com.project.Kdemy.dto.CourseRequestDto;
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

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
@Data

public class CourseController {

    private final CourseService courseService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<Course> createCourse(@RequestBody CourseRequestDto req, Authentication authentication) {

        System.out.println(authentication.getName());
        System.out.println(authentication.getDetails());

        Course course = courseService.CreateCourse(req, authentication.getName());
        return ResponseEntity.ok(course);
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllPublishedCourse() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PutMapping("/{courseId}/publish")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<String> publishCourse(@PathVariable Long courseId) {
        courseService.publishCourse(courseId);
        return ResponseEntity.status(200).body("Course Published");
    }

}
