package com.project.Kdemy.service.ServiceImpl;

import com.project.Kdemy.dto.SectionRequestDto;
import com.project.Kdemy.exception.ResourceNotFoundException;
import com.project.Kdemy.model.Course;
import com.project.Kdemy.model.Section;
import com.project.Kdemy.repository.CourseRepository;
import com.project.Kdemy.repository.EnrollmentRepository;
import com.project.Kdemy.repository.SectionRepository;
import com.project.Kdemy.service.SectionService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public Section createSection(Long courseId, SectionRequestDto req, String instructorEmail) {

        Course course = courseRepository.findById(courseId).orElseThrow(() ->
                new ResourceNotFoundException("Course not found"));

        if(!course.getInstructor().getEmail().equals(instructorEmail)){
            throw new ResourceNotFoundException("not your course");
        }

        Section section = new Section();
        section.setTitle(req.getTitle());
        section.setOrderIndex(req.getOrderIndex());
        section.setCourse(course);

        return sectionRepository.save(section);
    }

    @Override
    public @Nullable List<Section> getSections(Long courseId, String email) {

        if(!enrollmentRepository.existsByStudentEmailAndCourseId(email, courseId)){
            throw new RuntimeException("you are not enrolled to this Course");
        }

        List<Section> sections = sectionRepository.findByCourseId(courseId);

        return sections;
    }
}
