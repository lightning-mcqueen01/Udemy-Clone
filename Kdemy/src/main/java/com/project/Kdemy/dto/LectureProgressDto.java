package com.project.Kdemy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureProgressDto {

    private Long lectureId;
    private String lectureTitle;
    private Long watchedSeconds;
    private Long duration;
    private boolean completed;
    private int progressPercentage;

    // Section info
    private Long sectionId;
    private String sectionTitle;
}
