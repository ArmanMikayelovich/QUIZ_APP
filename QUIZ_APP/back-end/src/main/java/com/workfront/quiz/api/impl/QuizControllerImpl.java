package com.workfront.quiz.api.impl;

import com.workfront.quiz.api.QuizController;
import com.workfront.quiz.dto.question.QuestionDto;
import com.workfront.quiz.dto.quiz.QuizDto;
import com.workfront.quiz.entity.TopicEntity;
import com.workfront.quiz.service.QuizService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/quiz/") //TODO jshtel sen jishta te che
@CrossOrigin(value = "*")
public class QuizControllerImpl implements QuizController {

    private QuizService quizService;

    public QuizControllerImpl(QuizService quizService) {
        this.quizService = quizService;
    }

    @Override
    @GetMapping("{id}")
    public QuizDto findById(@PathVariable Long id) {
        return quizService.findById(id);
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
    public Collection<QuestionDto> startQuiz(@RequestParam Long upComingQuizId) {

        return quizService.generateQuiz(upComingQuizId);
    }
}
