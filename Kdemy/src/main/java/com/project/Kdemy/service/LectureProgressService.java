package com.project.Kdemy.service;

import com.project.Kdemy.model.LectureProgress;

public interface LectureProgressService {

    public LectureProgress updateProgress(
            Long lectureId,
            String studentEmail,
            int watchedSeconds,
            boolean completed);
}
