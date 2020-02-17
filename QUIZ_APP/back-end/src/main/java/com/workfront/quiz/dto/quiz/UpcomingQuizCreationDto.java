package com.workfront.quiz.dto.quiz;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpcomingQuizCreationDto {
    private Long userId;

    private Long topicId;

    private LocalDate deadline;

    private Integer durationInMinutes;

    private Long questionCount;
}
