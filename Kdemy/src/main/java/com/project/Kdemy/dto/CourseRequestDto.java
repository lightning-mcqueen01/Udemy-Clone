package com.project.Kdemy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CourseRequestDto {

    private String title;

    private String description;

    private Double price;

    private Long categoryId;
}
