package com.project.Kdemy.service.ServiceImpl;

import com.project.Kdemy.exception.AlreadyExistsException;
import com.project.Kdemy.exception.ResourceNotFoundException;
import com.project.Kdemy.model.Course;
import com.project.Kdemy.model.Enrollment;
import com.project.Kdemy.repository.CourseRepository;
import com.project.Kdemy.repository.EnrollmentRepository;
import com.project.Kdemy.service.EnrollmentService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Data
@AllArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final CourseRepository courseRepo;
    private final EnrollmentRepository enrollmentRepo;

    @Override
    public Enrollment enroll(Long courseId, String studentEmail) {

        Course course = courseRepo.findById(courseId).orElseThrow(
                () -> new ResourceNotFoundException("Course not found")
        );

        if(enrollmentRepo.existsByStudentEmailAndCourseId(studentEmail, courseId)){
            throw new AlreadyExistsException(("Already enrolled"));
        };

        Enrollment enrollment = new Enrollment();
        enrollment.setStudentEmail(studentEmail);
        enrollment.setCourse(course);
        enrollment.setEnrolledAt(LocalDateTime.now());
        return enrollmentRepo.save(enrollment);
    }

    @Override
    public List<Course> getMyCourses(String studentEmail) {

        return enrollmentRepo.findByStudentEmail(studentEmail)
                .stream()
                .map(Enrollment::getCourse)
                .toList();
    }
}
