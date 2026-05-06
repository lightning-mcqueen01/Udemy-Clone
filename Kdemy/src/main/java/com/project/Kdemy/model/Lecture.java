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
<<<<<<< HEAD
    private long duration;
=======
    private int duration;
>>>>>>> c85368aab4ccea7364855d8cb229bc169ca3ef19
    private String videoUrl;

    @ManyToOne
    @JoinColumn(name= "section_id")
    private Section section;


}
