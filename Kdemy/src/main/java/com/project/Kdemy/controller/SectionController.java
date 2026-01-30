package com.project.Kdemy.controller;


import com.project.Kdemy.dto.SectionRequestDto;
import com.project.Kdemy.service.ServiceImpl.SectionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sections")
@AllArgsConstructor
public class SectionController {

    private final SectionServiceImpl sectionService;

    @PostMapping("/courses/{courseId}")
    public ResponseEntity<?> createSection(@PathVariable Long courseId, @RequestBody SectionRequestDto req,
                                           Authentication auth) {
        return ResponseEntity.ok(sectionService.createSection(courseId, req, auth.getName()));
    }
}
