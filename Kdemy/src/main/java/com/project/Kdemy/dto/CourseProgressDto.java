package com.project.Kdemy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseProgressDto {

    private Long courseId;
    private String courseTitle;
    private String courseThumbnail;

    private Long lastLectureId;
    private String lastLectureTitle;
    private int lastWatchedSeconds;
    private int lastLectureDuration;

    private Long nextLectureId;
    private String nextLectureTitle;

    private int totalLectures;
    private int completedLectures;
    private int progressPercentage;


    private String lastWatchedAt;
}
