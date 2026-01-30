package com.project.Kdemy.service;

import com.project.Kdemy.dto.SectionRequestDto;
import com.project.Kdemy.model.Section;

public interface SectionService {

    public Section createSection(Long courseId, SectionRequestDto req, String instructorEmail);
}
