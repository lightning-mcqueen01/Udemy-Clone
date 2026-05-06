package com.project.Kdemy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseDto {

    private Long id;
    private String title;
    private String Description;
    private Double price;
    private String category;
private String instructor;
private LocalDateTime cratedAt;

}
