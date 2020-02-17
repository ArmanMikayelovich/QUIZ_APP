package com.workfront.quiz.service;

import com.workfront.quiz.dto.question.QuestionDto;
import com.workfront.quiz.dto.quiz.*;
import com.workfront.quiz.entity.TopicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface QuizService {

    QuizDto findById(Long id);

    Page<QuizDto> getAllQuizes(Pageable pageable);

    Page<QuizDto> getQuizesByTopic(TopicEntity topic, Pageable pageable);

    QuestionDto generateQuiz(Long topicId);

    void remove(Long id);

    Page<QuizDtoShortInfo> getQuizesByUserId(Long userId, Pageable pageable);

    PastQuizInfoDto getQuizInfo(Long quizId);

    Page<UpcomingQuizDto> getUpcomingQuizes(Long userId, Pageable pageable);

    void createUpcomingQuiz(UpcomingQuizCreationDto quizCreationDto);

    QuestionDto getNextQuestion(Long nextQuestionId);

}
