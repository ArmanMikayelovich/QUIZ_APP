package com.workfront.quiz.dto.quiz;

import com.workfront.quiz.dto.answer.AnswerDto;
import com.workfront.quiz.entity.QuizQuestionEntity;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class QuizQuestionDto {
    private Long id;
    private Long quizId;
    private Long questionId;
    private List<AnswerDto> answers;

    public static QuizQuestionDto mapFromEntity(QuizQuestionEntity entity) {
        QuizQuestionDto quizQuestionDto = new QuizQuestionDto();
        quizQuestionDto.id = entity.getId();
        quizQuestionDto.quizId = entity.getQuiz().getId();
        quizQuestionDto.questionId = entity.getQuestion().getId();
        quizQuestionDto.answers = entity.getGivenAnswers().stream()
                .map(AnswerDto::mapFromEntity).collect(Collectors.toList());
        return quizQuestionDto;
    }
}
