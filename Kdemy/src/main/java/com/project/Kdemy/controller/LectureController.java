package com.project.Kdemy.controller;

import com.project.Kdemy.dto.LectureRequestDto;
import com.project.Kdemy.model.Lecture;
import com.project.Kdemy.repository.LectureRepository;
import com.project.Kdemy.service.LectureService;
import com.project.Kdemy.service.ServiceImpl.LectureServiceImpl;
import com.project.Kdemy.service.ServiceImpl.VideoUploadServiceImpl;
import com.project.Kdemy.service.VideoUploadService;
import lombok.AllArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/Lecture")
@AllArgsConstructor
public class LectureController {

    private final LectureService lectureService;
    private final LectureRepository lectureRepository;
    private final VideoUploadService videoService;

    @PostMapping("/section/{sectionId}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<?> createLecture(
            @PathVariable Long sectionId,
            @RequestBody LectureRequestDto req) {

        return ResponseEntity.ok(
                lectureService.createLecture(sectionId, req)
        );
    }

    @PostMapping("/{lectureId}/upload")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<?> uploadVideo(
            @PathVariable Long lectureId,
            @RequestParam("file") MultipartFile file) throws IOException {

        String url = videoService.uploadVideo(file);

        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow();
        lecture.setVideoUrl(url);

        return ResponseEntity.ok(lectureRepository.save(lecture));
    }

    @GetMapping("/section/{sectionId}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<List<Lecture>> getLectureBySection(@PathVariable Long sectionId, Authentication auth) {

        return ResponseEntity.ok(lectureService.getLectureByService(sectionId, auth.getName()));
    }

    @GetMapping("/{lectureId}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<Lecture> getLecture(@PathVariable Long lectureId, Authentication auth) {

        return ResponseEntity.ok(lectureService.getLecture(lectureId, auth.getName()));
    }
}
