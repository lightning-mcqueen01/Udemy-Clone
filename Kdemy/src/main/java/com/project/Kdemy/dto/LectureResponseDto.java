package com.project.Kdemy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LectureResponseDto {

    private Long id;
    private String title;
    private String videoUrl;
<<<<<<< HEAD
    private Long duration;
=======
    private int duration;
>>>>>>> c85368aab4ccea7364855d8cb229bc169ca3ef19
    private Long sectionId;
    private String sectionTitle;

}
