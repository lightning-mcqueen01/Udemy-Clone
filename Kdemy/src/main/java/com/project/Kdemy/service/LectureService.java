package com.project.Kdemy.service;

import com.project.Kdemy.dto.LectureRequestDto;
import com.project.Kdemy.model.Lecture;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface LectureService {
    public Lecture createLecture(Long sectionId, LectureRequestDto req);

    @Nullable List<Lecture> getLectureByService(Long sectionId, String name);

    Lecture getLecture(Long lectureId, String name);
}
