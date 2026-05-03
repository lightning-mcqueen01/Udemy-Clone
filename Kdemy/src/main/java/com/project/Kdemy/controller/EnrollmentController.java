package com.project.Kdemy.controller;

import com.project.Kdemy.model.Course;
import com.project.Kdemy.service.EnrollmentService;
import com.project.Kdemy.service.ServiceImpl.EnrollmentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollment")
@PreAuthorize("hasRole('STUDENT')")
@AllArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/{courseId}")
    public ResponseEntity<?> enroll(
            @PathVariable Long courseId,
            Authentication auth) {

        return ResponseEntity.ok(
                enrollmentService.enroll(courseId, auth.getName())
        );
    }

    @GetMapping("/my-courses")
    public ResponseEntity<List<Course>> getMyCourses(Authentication auth) {
        return ResponseEntity.ok(
                enrollmentService.getMyCourses(auth.getName())
        );
    }
}
