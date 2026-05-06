package com.project.Kdemy.service;

import com.project.Kdemy.dto.CourseSearchDto;
import com.project.Kdemy.dto.CourseSearchResultDto;

import java.util.List;

public interface CourseSearchService {
    List<CourseSearchResultDto> searchCourses(CourseSearchDto searchDto);

    List<CourseSearchResultDto> getAutocompleteSuggestions(String query);

    List<CourseSearchResultDto> getCoursesByCategory(Long categoryId);

    List<CourseSearchResultDto> getTrendingCourses();
}
