package com.project.Kdemy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private long duration;
    private String videoUrl;

    @ManyToOne
    @JoinColumn(name= "section_id")
    private Section section;


}
