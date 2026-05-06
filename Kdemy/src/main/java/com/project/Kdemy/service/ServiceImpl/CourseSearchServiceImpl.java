package com.project.Kdemy.service.ServiceImpl;

import com.project.Kdemy.dto.CourseSearchDto;
import com.project.Kdemy.dto.CourseSearchResultDto;
import com.project.Kdemy.model.Course;
import com.project.Kdemy.model.CourseStatus;
import com.project.Kdemy.repository.CourseRepository;
import com.project.Kdemy.repository.EnrollmentRepository;
import com.project.Kdemy.repository.SectionRepository;
import com.project.Kdemy.service.CourseSearchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseSearchServiceImpl implements CourseSearchService {

    private final CourseRepository courseRepo;
    private final EnrollmentRepository enrollmentRepo;
    private final SectionRepository sectionRepository;

    // ── 1. Main Search Method ─────────────────────────────────────────────────
    @Override
    public List<CourseSearchResultDto> searchCourses(CourseSearchDto searchDto) {

        // Determine status filter
        CourseStatus statusFilter = null;
        if (searchDto.getPublishedOnly() != null && searchDto.getPublishedOnly()) {
            statusFilter = CourseStatus.PUBLISHED;
        }

        // Call repository search
        List<Course> courses = courseRepo.searchCourses(
                searchDto.getQuery(),
                searchDto.getCategoryId(),
                searchDto.getInstructorId(),
                searchDto.getMinPrice(),
                searchDto.getMaxPrice(),
                statusFilter
        );

        // Convert to DTOs
        List<CourseSearchResultDto> results = courses.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        // Apply sorting
        results = applySorting(results, searchDto.getSortBy(), searchDto.getSortDirection());

        return results;
    }

    // ── 2. Autocomplete Suggestions (fast title-only search) ─────────────────
    @Override
    public List<CourseSearchResultDto> getAutocompleteSuggestions(String query) {

        if (query == null || query.trim().isEmpty()) {
            return List.of();
        }

        List<Course> courses = courseRepo.searchByTitleOnly(query);

        // Limit to 10 results for autocomplete
        return courses.stream()
                .limit(10)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // ── 3. Get Courses by Category ───────────────────────────────────────────
    @Override
    public List<CourseSearchResultDto> getCoursesByCategory(Long categoryId) {

        List<Course> courses = courseRepo.findByCategoryId(categoryId);

        return courses.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // ── 4. Get Trending Courses (most enrollments) ───────────────────────────
    @Override
    public List<CourseSearchResultDto> getTrendingCourses() {

        List<Course> courses = courseRepo.findTrendingCourses();

        // Limit to top 10
        return courses.stream()
                .limit(10)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // ── Helper: Convert Course to DTO ────────────────────────────────────────
    private CourseSearchResultDto convertToDto(Course course) {

        CourseSearchResultDto dto = new CourseSearchResultDto();

        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setThumbnail(course.getThumbnailUrl());
        dto.setPrice(course.getPrice());
        dto.setStatus(course.getStatus());

        // Instructor details
        if (course.getInstructor() != null) {
            dto.setInstructorId(course.getInstructor().getId());
            dto.setInstructorName(course.getInstructor().getUsername());
        }

        // Category details
        if (course.getCategory() != null) {
            dto.setCategoryId(course.getCategory().getId());
            dto.setCategoryName(course.getCategory().getName());
        }

        // Calculate total lectures (from sections)
        int totalLectures = 0;
        if (sectionRepository.findByCourseId(course.getId()) != null) {
            totalLectures = sectionRepository.findByCourseId(course.getId()).stream()
                    .mapToInt(section -> section.getLectures() != null
                            ? section.getLectures().size()
                            : 0)
                    .sum();
        }
        dto.setTotalLectures(totalLectures);

        // Count enrollments
        // Note: This could be optimized with a COUNT query instead
        int enrollmentCount = enrollmentRepo.findAll().stream()
                .filter(e -> e.getCourse().getId().equals(course.getId()))
                .toList()
                .size();
        dto.setTotalEnrollments(enrollmentCount);

        // Timestamps
        if (course.getCreatedAt() != null) {
            dto.setCreatedAt(course.getCreatedAt()
                    .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }

        // Note: If you add reviews later, calculate average rating here
        dto.setAverageRating(0.0);  // Placeholder

        return dto;
    }

    // ── Helper: Apply Sorting ────────────────────────────────────────────────
    private List<CourseSearchResultDto> applySorting(
            List<CourseSearchResultDto> results,
            String sortBy,
            String sortDirection) {

        Comparator<CourseSearchResultDto> comparator = switch (sortBy.toLowerCase()) {
            case "price" -> Comparator.comparing(dto ->
                    dto.getPrice() != null ? dto.getPrice() : 0.0);
            case "title" -> Comparator.comparing(dto ->
                    dto.getTitle() != null ? dto.getTitle() : "");
            case "rating" -> Comparator.comparing(dto ->
                    dto.getAverageRating() != null ? dto.getAverageRating() : 0.0);
            case "enrollments" -> Comparator.comparing(dto ->
                    dto.getTotalEnrollments() != null ? dto.getTotalEnrollments() : 0);
            default -> Comparator.comparing(dto ->
                    dto.getCreatedAt() != null ? dto.getCreatedAt() : "");
        };

        // Choose comparator based on sortBy

        // Apply direction
        if ("ASC".equalsIgnoreCase(sortDirection)) {
            return results.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        } else {
            return results.stream()
                    .sorted(comparator.reversed())
                    .collect(Collectors.toList());
        }
    }
}