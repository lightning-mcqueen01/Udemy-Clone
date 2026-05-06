package com.project.Kdemy.service;

import com.project.Kdemy.dto.CourseProgressDto;
import com.project.Kdemy.dto.LectureProgressDto;
import com.project.Kdemy.model.LectureProgress;

import java.util.List;

public interface LectureProgressService {

    public LectureProgress updateProgress(
            Long lectureId,
            String studentEmail,
<<<<<<< HEAD
            Long watchedSeconds,
=======
            int watchedSeconds,
>>>>>>> c85368aab4ccea7364855d8cb229bc169ca3ef19
            boolean completed);

    CourseProgressDto getCourseProgress(String name, Long courseId);

    List<CourseProgressDto> getAllContinueWatching(String name);

    List<LectureProgressDto> getAllLectureProgress(String name, Long courseId);

    LectureProgressDto getLectureProgress(String name, Long lectureId);
}
