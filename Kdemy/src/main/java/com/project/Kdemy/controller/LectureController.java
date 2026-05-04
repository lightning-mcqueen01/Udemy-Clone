package com.project.Kdemy.controller;

import com.project.Kdemy.dto.LectureRequestDto;
import com.project.Kdemy.dto.LectureResponseDto;
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
    public ResponseEntity<LectureResponseDto> createLecture(
            @PathVariable Long sectionId,
            @RequestBody LectureRequestDto req) {

        Lecture lecture = lectureService.createLecture(sectionId, req);

        LectureResponseDto res = new LectureResponseDto();
        res.setId(lecture.getId());
        res.setTitle(lecture.getTitle());
        res.setDuration(lecture.getDuration());
        res.setSectionId(sectionId);
        res.setSectionTitle(lecture.getSection().getTitle());

        return ResponseEntity.ok(res);
    }

    @PostMapping("/{lectureId}/upload")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<LectureResponseDto> uploadVideo(
            @PathVariable Long lectureId,
            @RequestParam("file") MultipartFile file) throws IOException {

        String url = videoService.uploadVideo(file);

        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow();
        lecture.setVideoUrl(url);
        lectureRepository.save(lecture);

        LectureResponseDto res = new LectureResponseDto();
        res.setId(lecture.getId());
        res.setTitle(lecture.getTitle());
        res.setVideoUrl(lecture.getVideoUrl());
        res.setDuration(lecture.getDuration());
        res.setSectionId(lecture.getSection().getId());
        res.setSectionTitle(lecture.getSection().getTitle());

        return ResponseEntity.ok(res);
    }

    @GetMapping("/section/{sectionId}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<List<LectureResponseDto>> getLectureBySection(@PathVariable Long sectionId, Authentication auth) {

        List<Lecture> lectures = lectureService.getLectureByService(sectionId, auth.getName());

        List<LectureResponseDto> lectureDto =  lectures.stream().map(lecture -> new LectureResponseDto(
            lecture.getId(),
                lecture.getTitle(),
                lecture.getVideoUrl(),
                lecture.getDuration(),
                lecture.getSection().getId(),
                lecture.getSection().getTitle()
        )).toList();

        return ResponseEntity.ok(lectureDto);
    }

    @GetMapping("/{lectureId}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<LectureResponseDto> getLecture(@PathVariable Long lectureId, Authentication auth) {

        Lecture lecture = lectureService.getLecture(lectureId, auth.getName());

        LectureResponseDto response = new LectureResponseDto(
                lecture.getId(),
                lecture.getTitle(),
                lecture.getVideoUrl(),
                lecture.getDuration(),
                lecture.getSection().getId(),
                lecture.getSection().getTitle()
        );

        return ResponseEntity.ok(response);
    }
}
