package com.project.Kdemy.controller;

import com.project.Kdemy.dto.ReviewRequestDto;
import com.project.Kdemy.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{courseId}")
    public ResponseEntity<?> review(
            @PathVariable Long courseId,
            @RequestBody ReviewRequestDto dto,
            Authentication auth) {

        return ResponseEntity.ok(
                reviewService.addReview(courseId, dto, auth.getName())
        );
    }
}
