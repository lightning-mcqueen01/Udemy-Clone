package com.project.Kdemy.service.ServiceImpl;

import com.project.Kdemy.dto.SectionRequestDto;
import com.project.Kdemy.model.Course;
import com.project.Kdemy.model.Section;
import com.project.Kdemy.repository.CourseRepository;
import com.project.Kdemy.repository.SectionRepository;
import com.project.Kdemy.service.SectionService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final CourseRepository courseRepository;

    @Override
    public Section createSection(Long courseId, SectionRequestDto req, String instructorEmail) {

        Course course = courseRepository.findById(courseId).orElseThrow(() ->
                new RuntimeException("Course not found"));

        if(!course.getInstructor().getEmail().equals(instructorEmail)){
            throw new RuntimeException("not your course");
        }

        Section section = new Section();
        section.setTitle(req.getTitle());
        section.setOrderIndex(req.getOrderIndex());
        section.setCourse(course);

        return sectionRepository.save(section);
    }
}
