package com.project.Kdemy.controller;

import com.project.Kdemy.dto.LectureRequestDto;
import com.project.Kdemy.model.Lecture;
import com.project.Kdemy.repository.LectureRepository;
import com.project.Kdemy.service.ServiceImpl.LectureServiceImpl;
import com.project.Kdemy.service.ServiceImpl.VideoUploadServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/Lecture")
@PreAuthorize("hasRole('INSTRUCTOR')")
@AllArgsConstructor
public class LectureController {

    private final LectureServiceImpl lectureService;
    private final LectureRepository lectureRepository;
    private final VideoUploadServiceImpl videoService;

    @PostMapping("/section/{sectionId}")
    public ResponseEntity<?> createLecture(
            @PathVariable Long sectionId,
            @RequestBody LectureRequestDto req) {

        return ResponseEntity.ok(
                lectureService.createLecture(sectionId, req)
        );
    }

    @PostMapping("/{lectureId}/upload")
    public ResponseEntity<?> uploadVideo(
            @PathVariable Long lectureId,
            @RequestParam("file") MultipartFile file) throws IOException {

        String url = videoService.uploadVideo(file);

        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow();
        lecture.setVideoUrl(url);

        return ResponseEntity.ok(lectureRepository.save(lecture));
    }
}
