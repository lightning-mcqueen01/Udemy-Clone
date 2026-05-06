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
<<<<<<< HEAD
    private Long watchedSeconds;
    private Long duration;
=======
    private int watchedSeconds;
    private int duration;
>>>>>>> c85368aab4ccea7364855d8cb229bc169ca3ef19
    private boolean completed;
    private int progressPercentage;

    // Section info
    private Long sectionId;
    private String sectionTitle;
}
