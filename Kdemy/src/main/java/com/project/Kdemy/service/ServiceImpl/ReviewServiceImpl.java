package com.project.Kdemy.service.ServiceImpl;

import com.project.Kdemy.dto.ReviewRequestDto;
import com.project.Kdemy.exception.ResourceNotFoundException;
import com.project.Kdemy.model.Course;
import com.project.Kdemy.model.Review;
import com.project.Kdemy.repository.CourseRepository;
import com.project.Kdemy.repository.ReviewRepository;
import com.project.Kdemy.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepo;
    private final CourseRepository courseRepo;

    @Override
    public Review addReview(Long courseId, ReviewRequestDto dto, String email) {

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        Review review = new Review();
        review.setCourse(course);
        review.setStudentEmail(email);
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setCreatedAt(LocalDateTime.now());

        return reviewRepo.save(review);
    }
}
