package com.project.Kdemy.service.ServiceImpl;

import com.project.Kdemy.exception.ResourceNotFoundException;
import com.project.Kdemy.model.Lecture;
import com.project.Kdemy.model.LectureProgress;
import com.project.Kdemy.repository.LectureProgressRepository;
import com.project.Kdemy.repository.LectureRepository;
import com.project.Kdemy.service.LectureProgressService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class LectureProgressServiceImpl implements LectureProgressService {

    private final LectureProgressRepository progressRepo;
    private final LectureRepository lectureRepo;

    @Override
    public LectureProgress updateProgress(
            Long lectureId,
            String studentEmail,
            int watchedSeconds,
            boolean completed) {

        Lecture lecture = lectureRepo.findById(lectureId)
                .orElseThrow(() -> new ResourceNotFoundException("Lecture not found"));

        LectureProgress progress = progressRepo
                .findByStudentEmailAndLectureId(studentEmail, lectureId)
                .orElse(new LectureProgress());

        progress.setLecture(lecture);
        progress.setStudentEmail(studentEmail);
        progress.setWatchedSeconds(watchedSeconds);
        progress.setCompleted(completed);

        return progressRepo.save(progress);
    }
}
