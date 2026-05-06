package com.project.Kdemy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="sections")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int orderIndex;


    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    private List<Lecture> lectures = new ArrayList<>();



}
