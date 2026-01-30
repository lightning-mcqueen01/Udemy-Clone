package com.project.Kdemy.repository;

import com.project.Kdemy.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
