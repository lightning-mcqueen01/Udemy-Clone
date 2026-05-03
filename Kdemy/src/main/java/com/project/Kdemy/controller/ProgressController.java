package com.project.Kdemy.controller;

import com.project.Kdemy.dto.ProgressRequestDto;
import com.project.Kdemy.service.LectureProgressService;
import com.project.Kdemy.service.ServiceImpl.LectureProgressServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/progress")
@PreAuthorize("hasRole('STUDENT')")
@AllArgsConstructor
public class ProgressController {

    private final LectureProgressService progressService;

    @PostMapping("/{lectureId}")
    public ResponseEntity<?> update(
            @PathVariable Long lectureId,
            @RequestBody ProgressRequestDto dto,
            Authentication auth) {

        return ResponseEntity.ok(
                progressService.updateProgress(
                        lectureId,
                        auth.getName(),
                        dto.getWatchedSeconds(),
                        dto.isCompleted()
                )
        );
    }
}
