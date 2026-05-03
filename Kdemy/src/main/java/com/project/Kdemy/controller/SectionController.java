package com.project.Kdemy.controller;


import com.project.Kdemy.dto.SectionRequestDto;
import com.project.Kdemy.exception.ResourceNotFoundException;
import com.project.Kdemy.model.Section;
import com.project.Kdemy.repository.EnrollmentRepository;
import com.project.Kdemy.repository.SectionRepository;
import com.project.Kdemy.service.SectionService;
import com.project.Kdemy.service.ServiceImpl.SectionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sections")
@PreAuthorize("hasRole('INSTRUCTOR')")
@AllArgsConstructor
public class SectionController {

    private final SectionService sectionService;
    private final EnrollmentRepository enrollmentRepository;
    private final SectionRepository sectionRepository;

    @PostMapping("/courses/{courseId}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<?> createSection(@PathVariable Long courseId, @RequestBody SectionRequestDto req,
                                           Authentication auth) {
        return ResponseEntity.ok(sectionService.createSection(courseId, req, auth.getName()));
    }

    @GetMapping("/courses/{courseId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<Section>> getSections(@PathVariable Long courseId, Authentication auth) {

        return ResponseEntity.ok(sectionService.getSections(courseId, auth.getName()));

    }
}
