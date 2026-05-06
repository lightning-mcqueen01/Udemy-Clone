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
    private Long duration;
    private Long sectionId;
    private String sectionTitle;

}
