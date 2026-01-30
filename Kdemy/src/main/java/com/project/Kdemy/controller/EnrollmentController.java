package com.project.Kdemy.controller;

import com.project.Kdemy.service.ServiceImpl.EnrollmentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enrollment")
@PreAuthorize("hasRole('STUDENT')")
@AllArgsConstructor
public class EnrollmentController {

    private final EnrollmentServiceImpl enrollmentService;

    @PostMapping("/{courseId}")
    public ResponseEntity<?> enroll(
            @PathVariable Long courseId,
            Authentication auth) {

        return ResponseEntity.ok(
                enrollmentService.enroll(courseId, auth.getName())
        );
    }
}
