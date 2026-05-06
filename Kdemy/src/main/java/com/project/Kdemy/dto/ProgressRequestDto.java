package com.project.Kdemy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgressRequestDto {

<<<<<<< HEAD
    private Long watchedSeconds;
=======
    private int watchedSeconds;
>>>>>>> c85368aab4ccea7364855d8cb229bc169ca3ef19

    private boolean isCompleted;
}
