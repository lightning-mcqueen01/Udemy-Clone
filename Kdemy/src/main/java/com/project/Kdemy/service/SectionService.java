package com.project.Kdemy.service;

import com.project.Kdemy.dto.SectionRequestDto;
import com.project.Kdemy.model.Section;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface SectionService {

    public Section createSection(Long courseId, SectionRequestDto req, String instructorEmail);

    @Nullable List<Section> getSections(Long courseId, String name);
}
