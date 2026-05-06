package com.project.Kdemy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseSearchDto {

    private String query;

    private Long categoryId;

    private Long instructorId;
    private Double minPrice;
    private Double maxPrice;

    private Boolean publishedOnly = true;

    private String sortBy = "createdAt";
    private String sortDirection = "DESC";
}
