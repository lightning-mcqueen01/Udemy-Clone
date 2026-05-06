package com.project.Kdemy.service.ServiceImpl;

import com.project.Kdemy.dto.CourseProgressDto;
import com.project.Kdemy.dto.LectureProgressDto;
import com.project.Kdemy.exception.ResourceNotFoundException;
import com.project.Kdemy.model.Course;
import com.project.Kdemy.model.Lecture;
import com.project.Kdemy.model.LectureProgress;
import com.project.Kdemy.model.Section;
import com.project.Kdemy.repository.*;
import com.project.Kdemy.service.LectureProgressService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class LectureProgressServiceImpl implements LectureProgressService {

    private final LectureProgressRepository progressRepo;
    private final LectureRepository lectureRepo;
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final SectionRepository sectionRepository;

    @Override
    public LectureProgress updateProgress(
            Long lectureId,
            String studentEmail,
<<<<<<< HEAD
            Long watchedSeconds,
=======
            int watchedSeconds,
>>>>>>> c85368aab4ccea7364855d8cb229bc169ca3ef19
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

    @Override
    public CourseProgressDto getCourseProgress(String email, Long courseId) {

        if(!enrollmentRepository.existsByStudentEmailAndCourseId(email, courseId)){
            throw new RuntimeException("you are not enrolled in this course");
        }

        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new ResourceNotFoundException("course not found "));

        List<LectureProgress> progressList = progressRepo.findByStudentEmailAndCourseId(email, courseId);

        int totalLectures = sectionRepository.findByCourseId(courseId).stream()
                .mapToInt(section -> section.getLectures().size())
                .sum();

        int completedLectures = (int) progressList.stream()
                .filter(LectureProgress::isCompleted)
                .count();

        int progressPercentage = totalLectures > 0
                ? (completedLectures * 100) / totalLectures
                : 0;

        List<LectureProgress> lastWatchedList = progressRepo
                .findLastWatchedInCourse(email, courseId);

        CourseProgressDto dto = new CourseProgressDto();
        dto.setCourseId(course.getId());
        dto.setCourseTitle(course.getTitle());
        dto.setCourseThumbnail(course.getThumbnailUrl());
        dto.setTotalLectures(totalLectures);
        dto.setCompletedLectures(completedLectures);
        dto.setProgressPercentage(progressPercentage);

        if (!lastWatchedList.isEmpty()) {
            LectureProgress lastProgress = lastWatchedList.getFirst();
            Lecture lastLecture = lastProgress.getLecture();

            dto.setLastLectureId(lastLecture.getId());
            dto.setLastLectureTitle(lastLecture.getTitle());
            dto.setLastWatchedSeconds(lastProgress.getWatchedSeconds());
            dto.setLastLectureDuration(lastLecture.getDuration());
            dto.setLastWatchedAt(lastProgress.getLastWatchedAt()
                    .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

            // Find next unwatched lecture
            Lecture nextLecture = findNextUnwatchedLecture(course, progressList, lastLecture);
            if (nextLecture != null) {
                dto.setNextLectureId(nextLecture.getId());
                dto.setNextLectureTitle(nextLecture.getTitle());
            }
        } else {
            // No progress yet — suggest first lecture
            Lecture firstLecture = getFirstLectureInCourse(course);
            if (firstLecture != null) {
                dto.setNextLectureId(firstLecture.getId());
                dto.setNextLectureTitle(firstLecture.getTitle());
            }
        }

        return dto;

    }

    @Override
    public List<CourseProgressDto> getAllContinueWatching(String email) {

        List<Long> courseIds = progressRepo.findAllCourseIdsWithProgress(email);

        List<CourseProgressDto> result = new ArrayList<>();
        for (Long courseId : courseIds) {
            try {
                CourseProgressDto progress = getCourseProgress(email, courseId);
                result.add(progress);
            } catch (Exception e) {
                continue;
            }
        }

        return result;
    }

    @Override
    public List<LectureProgressDto> getAllLectureProgress(String email, Long courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        List<LectureProgress> progressList = progressRepo
                .findByStudentEmailAndCourseId(email, courseId);

        List<LectureProgressDto> result = new ArrayList<>();

        for (Section section : sectionRepository.findByCourseId(courseId)) {
            for (Lecture lecture : section.getLectures()) {

                // Find progress for this lecture
                Optional<LectureProgress> progressOpt = progressList.stream()
                        .filter(p -> p.getLecture().getId().equals(lecture.getId()))
                        .findFirst();

                LectureProgressDto dto = new LectureProgressDto();
                dto.setLectureId(lecture.getId());
                dto.setLectureTitle(lecture.getTitle());
                dto.setDuration(lecture.getDuration());
                dto.setSectionId(section.getId());
                dto.setSectionTitle(section.getTitle());

                if (progressOpt.isPresent()) {
                    LectureProgress progress = progressOpt.get();
                    dto.setWatchedSeconds(progress.getWatchedSeconds());
                    dto.setCompleted(progress.isCompleted());

                    // Calculate progress percentage
<<<<<<< HEAD
                    if (lecture.getDuration() != 0 && lecture.getDuration() > 0) {
                        int percentage = (int) ((progress.getWatchedSeconds() * 100)
                                / lecture.getDuration());
=======
                    if (lecture.getDuration() != null && lecture.getDuration() > 0) {
                        int percentage = (progress.getWatchedSeconds() * 100)
                                / lecture.getDuration().intValue();
>>>>>>> c85368aab4ccea7364855d8cb229bc169ca3ef19
                        dto.setProgressPercentage(Math.min(percentage, 100));
                    }
                } else {
                    // No progress yet
<<<<<<< HEAD
                    dto.setWatchedSeconds(0L);
=======
                    dto.setWatchedSeconds(0);
>>>>>>> c85368aab4ccea7364855d8cb229bc169ca3ef19
                    dto.setCompleted(false);
                    dto.setProgressPercentage(0);
                }

                result.add(dto);
            }
        }

        return result;
    }

    @Override
    public LectureProgressDto getLectureProgress(String email, Long lectureId) {

        Lecture lecture = lectureRepo.findById(lectureId)
                .orElseThrow(() -> new RuntimeException("Lecture not found"));

        Optional<LectureProgress> progressOpt = progressRepo
                .findByStudentEmailAndLectureId(email, lectureId);

        LectureProgressDto dto = new LectureProgressDto();
        dto.setLectureId(lecture.getId());
        dto.setLectureTitle(lecture.getTitle());
        dto.setDuration(lecture.getDuration());
        dto.setSectionId(lecture.getSection().getId());
        dto.setSectionTitle(lecture.getSection().getTitle());

        if (progressOpt.isPresent()) {
            LectureProgress progress = progressOpt.get();
            dto.setWatchedSeconds(progress.getWatchedSeconds());
            dto.setCompleted(progress.isCompleted());

<<<<<<< HEAD
            if (lecture.getDuration() != 0 && lecture.getDuration() > 0) {
                int percentage = (int) ((progress.getWatchedSeconds() * 100)
                        / lecture.getDuration());
                dto.setProgressPercentage(Math.min(percentage, 100));
            }
        } else {
            dto.setWatchedSeconds(0L);
=======
            if (lecture.getDuration() != null && lecture.getDuration() > 0) {
                int percentage = (progress.getWatchedSeconds() * 100)
                        / lecture.getDuration().intValue();
                dto.setProgressPercentage(Math.min(percentage, 100));
            }
        } else {
            dto.setWatchedSeconds(0);
>>>>>>> c85368aab4ccea7364855d8cb229bc169ca3ef19
            dto.setCompleted(false);
            dto.setProgressPercentage(0);
        }

        return dto;
    }

    private Lecture findNextUnwatchedLecture(
            Course course,
            List<LectureProgress> progressList,
            Lecture currentLecture) {

        boolean foundCurrent = false;

        for (Section section : sectionRepository.findByCourseId(course.getId())) {
            for (Lecture lecture : section.getLectures()) {

                if (!foundCurrent) {
                    if (lecture.getId().equals(currentLecture.getId())) {
                        foundCurrent = true;
                    }
                    continue;
                }

                boolean isCompleted = progressList.stream()
                        .anyMatch(p -> p.getLecture().getId().equals(lecture.getId())
                                && p.isCompleted());

                if (!isCompleted) {
                    return lecture;
                }
            }
        }

        return null;
    }

    private Lecture getFirstLectureInCourse(Course course) {
        return sectionRepository.findByCourseId(course.getId()).stream()
                .filter(section -> !section.getLectures().isEmpty())
                .map(section -> section.getLectures().getFirst())
                .findFirst()
                .orElse(null);
    }
}
