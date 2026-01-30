package com.project.Kdemy.service;

import com.project.Kdemy.model.Enrollment;

public interface EnrollmentService {

    public Enrollment enroll(Long courseId, String studentEmail);
}
