package com.project.Kdemy.service.ServiceImpl;

import com.project.Kdemy.dto.LectureRequestDto;
import com.project.Kdemy.exception.ResourceNotFoundException;
import com.project.Kdemy.model.Lecture;
import com.project.Kdemy.model.Section;
import com.project.Kdemy.repository.EnrollmentRepository;
import com.project.Kdemy.repository.LectureRepository;
import com.project.Kdemy.repository.SectionRepository;
import com.project.Kdemy.service.LectureService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.FileSystemNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
@Data
public class LectureServiceImpl implements LectureService {

    private final SectionRepository sectionRepository;
    private final LectureRepository lectureRepository;
    private final EnrollmentRepository enrollmentRepository;


    @Override
    public Lecture createLecture(Long sectionId, LectureRequestDto req) {

        Section section = sectionRepository.findById(sectionId).orElseThrow(
                () -> new ResourceNotFoundException("Section not found")
        );

        Lecture lecture = new Lecture();
        lecture.setTitle(req.getTitle());
        lecture.setSection(section);

        return lectureRepository.save(lecture);
    }

    @Override
    public @Nullable List<Lecture> getLectureByService(Long sectionId, String email) {

        Section section = sectionRepository.findById(sectionId).orElseThrow(() ->
                new ResourceNotFoundException("Section not found"));

        Long courseId = section.getCourse().getId();

        if (!enrollmentRepository.existsByStudentEmailAndCourseId(email, courseId)) {
            throw new ResourceNotFoundException("You are not enrolled in this course");
        }

        return section.getLectures();
    }

    @Override
    public Lecture getLecture(Long lectureId, String email) {

        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new ResourceNotFoundException("Lecture not found"));

        Long courseId = lecture.getSection().getCourse().getId();

        // Enrollment check
        if (!enrollmentRepository.existsByStudentEmailAndCourseId(email, courseId)) {
            throw new ResourceNotFoundException("You are not enrolled in this course");
        }

        return lecture;
    }
}
