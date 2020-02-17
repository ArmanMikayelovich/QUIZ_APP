package com.workfront.quiz.api.impl;

import com.workfront.quiz.api.QuizController;
import com.workfront.quiz.dto.question.QuestionDto;
import com.workfront.quiz.dto.quiz.*;
import com.workfront.quiz.entity.TopicEntity;
import com.workfront.quiz.service.QuizService;
import com.workfront.quiz.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/quiz/") //TODO jshtel sen jishta te che
@CrossOrigin(value = "*")
@AllArgsConstructor
public class QuizControllerImpl implements QuizController {

    private QuizService quizService;
    private UserService userService;

    @Override
    @GetMapping("{id}")
    public PastQuizInfoDto findById(@PathVariable Long id) {
        return quizService.getQuizInfo(id);
    }

    @Override
    @GetMapping("all")
    public Page<QuizDto> getAllQuizes(@PageableDefault Pageable pageable) {
        return quizService.getAllQuizes(pageable);
    }

    @Override
    @GetMapping("{topic}") //TODO jshtel sen jishta te che
    public Page<QuizDto> getQuizesByTopic(@PathVariable TopicEntity topic, @PageableDefault Pageable pageable) {
        return quizService.getQuizesByTopic(topic, pageable);
    }

    @Override
    @DeleteMapping("{id}")
    public void remove(@PathVariable Long id) {
        quizService.remove(id);
    }

    @Override
    @GetMapping("start")
    public QuestionDto startQuiz(@RequestParam Long upComingQuizId) {

        return quizService.generateQuiz(upComingQuizId);
    }

    @Override
    @GetMapping("get-question")
    public QuestionDto getQuestion(@RequestParam Long nextQuestionId) {
        return quizService.getNextQuestion(nextQuestionId);
    }

    @Override
    @PostMapping("finish")
    public PastQuizInfoDto finishQuiz(@RequestParam Long quizId){
        //TODO compute answers and set successPercent
       return quizService.getQuizInfo(quizId);
    }

    @Override
    @GetMapping("own")
    public Page<QuizDtoShortInfo> getQuizesForAuthenticatedUser(@PageableDefault Pageable pageable) {
        return quizService.getQuizesByUserId(userService.getMe(), pageable);
    }

    @Override
    @GetMapping("user/{userId}")
    @PreAuthorize(value = "hasAnyAuthority('ADMIN,OBSERVER')")
    public Page<QuizDtoShortInfo> getQuizesForUser(@PathVariable Long userId,
                                                   @PageableDefault Pageable pageable) {
        return quizService.getQuizesByUserId(userId, pageable);
    }

    @Override
    @GetMapping("upcoming/own")
    public Page<UpcomingQuizDto> getUpcomingQuizForAuthenticatedUser(Pageable pageable) {
        return quizService.getUpcomingQuizes(userService.getMe(), pageable);
    }

    @Override
    @GetMapping("upcoming/user/{userId}")
    @PreAuthorize(value = "hasAnyAuthority('ADMIN,OBSERVER')")
    public Page<UpcomingQuizDto> getUpcomingQuizForUser(@PathVariable Long userId, @PageableDefault Pageable pageable) {
        return quizService.getUpcomingQuizes(userId, pageable);
    }

    @Override
    @PostMapping("create-upcoming-quiz")
    public void createUpcomingQuizForUser(@RequestBody UpcomingQuizCreationDto upcomingQuizCreationDto) {
        quizService.createUpcomingQuiz(upcomingQuizCreationDto);
    }
}
