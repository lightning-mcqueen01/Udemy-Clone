package com.project.Kdemy.service;

import com.project.Kdemy.dto.LectureRequestDto;
import com.project.Kdemy.model.Lecture;

public interface LectureService {
    public Lecture createLecture(Long sectionId, LectureRequestDto req);
}
