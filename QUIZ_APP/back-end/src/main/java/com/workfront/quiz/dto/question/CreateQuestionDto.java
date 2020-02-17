package com.workfront.quiz.dto.question;

import com.workfront.quiz.dto.answer.CreateAnswerDto;
import com.workfront.quiz.entity.AnswerEntity;
import com.workfront.quiz.entity.QuestionEntity;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CreateQuestionDto {

    private Long topicId;

    private String text;

    private List<CreateAnswerDto> createAnswerDtoList;

    public QuestionEntity toEntity() {

        QuestionEntity question = new QuestionEntity();
        List<AnswerEntity> answerEntities =
                createAnswerDtoList
                        .stream()
                        .map(CreateAnswerDto::toEntity)
                        .collect(Collectors.toList());

        question.setAnswers(answerEntities);
        question.setText(this.text);
        return question;
    }
}
