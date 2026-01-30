package com.project.Kdemy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
public class Lecture {

    private Long id;

    private String title;
    private int duration;
    private String videoUrl;

    @ManyToOne
    @JoinColumn(name= "section_id")
    private Section section;


}
