package com.workfront.quiz.api;

import com.workfront.quiz.dto.question.QuestionDto;
import com.workfront.quiz.dto.quiz.*;
import com.workfront.quiz.entity.TopicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.soap.SOAPBinding;
import java.util.Collection;

public interface QuizController {

    PastQuizInfoDto findById(Long id);

    Page<QuizDto> getAllQuizes(Pageable pageable);

    Page<QuizDto> getQuizesByTopic(TopicEntity topic, Pageable pageable);

    void remove(Long id);

    QuestionDto startQuiz(Long upComingQuizId);


    @GetMapping("question")
    QuestionDto getQuestion(@RequestParam Long nextQuestionId);

    @PostMapping("finish")
    PastQuizInfoDto finishQuiz(@RequestParam Long quizId);

    Page<QuizDtoShortInfo> getQuizesForAuthenticatedUser(Pageable pageable);

    Page<QuizDtoShortInfo> getQuizesForUser(Long userId, Pageable pageable);

    Page<UpcomingQuizDto> getUpcomingQuizForAuthenticatedUser(Pageable pageable);

    Page<UpcomingQuizDto> getUpcomingQuizForUser(Long userId, Pageable pageable);

    void createUpcomingQuizForUser(UpcomingQuizCreationDto upcomingQuizCreationDto);
}
