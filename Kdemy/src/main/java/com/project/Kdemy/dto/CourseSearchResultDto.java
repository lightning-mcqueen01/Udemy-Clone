package com.project.Kdemy.dto;

import com.project.Kdemy.model.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseSearchResultDto {

    private Long id;
    private String title;
    private String description;
    private String thumbnail;
    private Double price;
    private CourseStatus status;

    // Instructor details
    private Long instructorId;
    private String instructorName;

    // Category details
    private Long categoryId;
    private String categoryName;

    // Stats
    private Integer totalLectures;
    private Integer totalEnrollments;
    private Double averageRating;  // If you have reviews

    // Timestamps
    private String createdAt;
    private String updatedAt;
}
