package com.project.Kdemy.service;

import com.project.Kdemy.model.Course;
import com.project.Kdemy.model.Enrollment;

import java.util.List;

public interface EnrollmentService {

    public Enrollment enroll(Long courseId, String studentEmail);

    public List<Course> getMyCourses(String studentEmail);
}
