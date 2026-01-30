package com.project.Kdemy.service.ServiceImpl;

import com.project.Kdemy.dto.LectureRequestDto;
import com.project.Kdemy.model.Lecture;
import com.project.Kdemy.model.Section;
import com.project.Kdemy.repository.LectureRepository;
import com.project.Kdemy.repository.SectionRepository;
import com.project.Kdemy.service.LectureService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Data
public class LectureServiceImpl implements LectureService {

    private final SectionRepository sectionRepository;
    private final LectureRepository lectureRepository;

    @Override
    public Lecture createLecture(Long sectionId, LectureRequestDto req) {

        Section section = sectionRepository.findById(sectionId).orElseThrow(
                () -> new RuntimeException("Section not found")
        );

        Lecture lecture = new Lecture();
        lecture.setTitle(req.getTitle());
        lecture.setSection(section);

        return lectureRepository.save(lecture);
    }
}
