package com.project.Kdemy.controller;

import com.project.Kdemy.dto.CourseSearchDto;
import com.project.Kdemy.dto.CourseSearchResultDto;
import com.project.Kdemy.service.CourseSearchService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class CourseSearchController {

    private final CourseSearchService searchService;

    // ── 1. Main Search Endpoint ──────────────────────────────────────────────
    // GET /search/courses?query=spring&categoryId=1&minPrice=0&maxPrice=100&sortBy=price
    @GetMapping("/courses")
    public ResponseEntity<List<CourseSearchResultDto>> searchCourses(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long instructorId,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false, defaultValue = "true") Boolean publishedOnly,
            @RequestParam(required = false, defaultValue = "createdAt") String sortBy,
            @RequestParam(required = false, defaultValue = "DESC") String sortDirection) {

        CourseSearchDto searchDto = new CourseSearchDto();
        searchDto.setQuery(query);
        searchDto.setCategoryId(categoryId);
        searchDto.setInstructorId(instructorId);
        searchDto.setMinPrice(minPrice);
        searchDto.setMaxPrice(maxPrice);
        searchDto.setPublishedOnly(publishedOnly);
        searchDto.setSortBy(sortBy);
        searchDto.setSortDirection(sortDirection);

        List<CourseSearchResultDto> results = searchService.searchCourses(searchDto);
        return ResponseEntity.ok(results);
    }

    // ── 2. Autocomplete Endpoint (fast, title-only) ──────────────────────────
    // GET /search/autocomplete?q=spring
    @GetMapping("/autocomplete")
    public ResponseEntity<List<CourseSearchResultDto>> autocomplete(
            @RequestParam("q") String query) {

        List<CourseSearchResultDto> results = searchService.getAutocompleteSuggestions(query);
        return ResponseEntity.ok(results);
    }

    // ── 3. Get Courses by Category ───────────────────────────────────────────
    // GET /search/category/1
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<CourseSearchResultDto>> getCoursesByCategory(
            @PathVariable Long categoryId) {

        List<CourseSearchResultDto> results = searchService.getCoursesByCategory(categoryId);
        return ResponseEntity.ok(results);
    }

    // ── 4. Get Trending Courses ──────────────────────────────────────────────
    // GET /search/trending
    @GetMapping("/trending")
    public ResponseEntity<List<CourseSearchResultDto>> getTrendingCourses() {

        List<CourseSearchResultDto> results = searchService.getTrendingCourses();
        return ResponseEntity.ok(results);
    }

    // ── 5. Advanced Search with POST body (for complex queries) ──────────────
    @PostMapping("/advanced")
    public ResponseEntity<List<CourseSearchResultDto>> advancedSearch(
            @RequestBody CourseSearchDto searchDto) {

        List<CourseSearchResultDto> results = searchService.searchCourses(searchDto);
        return ResponseEntity.ok(results);
    }
}