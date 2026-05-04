package com.project.Kdemy.service;

import com.project.Kdemy.dto.CourseProgressDto;
import com.project.Kdemy.dto.LectureProgressDto;
import com.project.Kdemy.model.LectureProgress;

import java.util.List;

public interface LectureProgressService {

    public LectureProgress updateProgress(
            Long lectureId,
            String studentEmail,
            int watchedSeconds,
            boolean completed);

    CourseProgressDto getCourseProgress(String name, Long courseId);

    List<CourseProgressDto> getAllContinueWatching(String name);

    List<LectureProgressDto> getAllLectureProgress(String name, Long courseId);

    LectureProgressDto getLectureProgress(String name, Long lectureId);
}
