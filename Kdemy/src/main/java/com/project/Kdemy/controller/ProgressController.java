package com.project.Kdemy.controller;

import com.project.Kdemy.dto.CourseProgressDto;
import com.project.Kdemy.dto.LectureProgressDto;
import com.project.Kdemy.dto.ProgressRequestDto;
import com.project.Kdemy.service.LectureProgressService;
import com.project.Kdemy.service.ServiceImpl.LectureProgressServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/continue/{courseId}")
    public ResponseEntity<CourseProgressDto> getContinueWatching(@PathVariable Long courseId,
                        Authentication auth) {

        CourseProgressDto progress = progressService.getCourseProgress(
                auth.getName(),
                courseId
        );
        return ResponseEntity.ok(progress);

    }

    @GetMapping("/continue-watching")
    public ResponseEntity<List<CourseProgressDto>> getAllContinueWatching(
            Authentication auth) {

        List<CourseProgressDto> courses = progressService.getAllContinueWatching(
                auth.getName()
        );
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/course/{courseId}/lectures")
    public ResponseEntity<List<LectureProgressDto>> getCourseProgress(
            @PathVariable Long courseId,
            Authentication auth) {

        List<LectureProgressDto> progress = progressService.getAllLectureProgress(
                auth.getName(),
                courseId
        );
        return ResponseEntity.ok(progress);
    }

    @GetMapping("/lecture/{lectureId}")
    public ResponseEntity<LectureProgressDto> getLectureProgress(
            @PathVariable Long lectureId,
            Authentication auth) {

        LectureProgressDto progress = progressService.getLectureProgress(
                auth.getName(),
                lectureId
        );
        return ResponseEntity.ok(progress);
    }
}
