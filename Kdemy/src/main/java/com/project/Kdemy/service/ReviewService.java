package com.project.Kdemy.service;

import com.project.Kdemy.dto.ReviewRequestDto;
import com.project.Kdemy.model.Review;

public interface ReviewService {

    public Review addReview(Long courseId, ReviewRequestDto dto, String email);
}
