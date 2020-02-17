package com.workfront.quiz.dto.answer;

import com.workfront.quiz.entity.AnswerEntity;
import lombok.Data;

@Data
public class AnswerDto {
    private Long id;
    private String text;

    public static AnswerDto mapFromEntity(AnswerEntity answerEntity) {
        AnswerDto answerDto = new AnswerDto();

        answerDto.id = answerEntity.getId();
        answerDto.text = answerEntity.getText();
        return answerDto;
    }

}
