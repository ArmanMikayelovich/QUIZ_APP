package com.workfront.quiz.api;

import com.workfront.quiz.dto.question.CreateQuestionDto;
import com.workfront.quiz.dto.question.QuestionDto;
import com.workfront.quiz.entity.QuestionEntity;
import com.workfront.quiz.entity.TopicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionController {

    QuestionDto findById(Long id);

    Page<QuestionDto> searchByText(String text, Pageable pageable);

    Page<QuestionDto> getAllQuestions(Pageable pageable);

    Page<QuestionDto> getQuestionsByTopic(TopicEntity topic, Pageable pageable);

    void remove(Long id);

    void update(QuestionDto question);

    void create(CreateQuestionDto question);
}
