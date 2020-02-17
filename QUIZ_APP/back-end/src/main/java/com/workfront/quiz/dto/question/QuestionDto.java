package com.workfront.quiz.dto.question;

import com.workfront.quiz.dto.answer.AnswerDto;
import com.workfront.quiz.entity.QuestionEntity;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class QuestionDto {

    private Long id;

    private Long topicId;

    private String text;

    private List<AnswerDto> answers;

    private Boolean isMultiAnswer;

    private Long nextQuestionId;

    public QuestionEntity toEntity() {
        QuestionEntity question = new QuestionEntity();
        question.setText(this.text);

        return question;
    }


    public static QuestionDto mapFromEntity(QuestionEntity question) {

        QuestionDto questionDto = new QuestionDto();

        questionDto.setId(question.getId());
        questionDto.setText(question.getText());
        questionDto.setTopicId(question.getTopic().getId());
        questionDto.setAnswers(question.getAnswers().stream()
                .map(AnswerDto::mapFromEntity).collect(Collectors.toList()));
        questionDto.setIsMultiAnswer(question.getIsMultiAnswer());
        return questionDto;
    }


}
