package com.workfront.quiz.api.impl;

import com.workfront.quiz.api.QuestionController;
import com.workfront.quiz.dto.question.CreateQuestionDto;
import com.workfront.quiz.dto.question.QuestionDto;
import com.workfront.quiz.entity.TopicEntity;
import com.workfront.quiz.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/question/")
@CrossOrigin(value = "*")
public class QuestionControllerImpl implements QuestionController {

    private QuestionService questionService;

    public QuestionControllerImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    @GetMapping("{id}")
    public QuestionDto findById(@PathVariable Long id) {
        return questionService.findById(id);
    }

    @Override
    @GetMapping("search")
    public Page<QuestionDto> searchByText(@RequestParam String text, @PageableDefault Pageable pageable) {
        return questionService.searchByText(text, pageable);
    }

    @Override
    @GetMapping("all")
    public Page<QuestionDto> getAllQuestions(@PageableDefault Pageable pageable) {
        return questionService.getAllQuestions(pageable);
    }

    @Override
    @GetMapping("{topic}")
    public Page<QuestionDto> getQuestionsByTopic(@PathVariable TopicEntity topic, @PageableDefault Pageable pageable) {
        return questionService.getQuestionsByTopic(topic, pageable);
    }

    @Override
    @DeleteMapping("{id}")
    public void remove(@PathVariable Long id) {
        questionService.remove(id);
    }

    @Override
    @PutMapping("update")
    public void update(QuestionDto question) {
        questionService.update(question);
    }

    @Override
    @PostMapping("create")
    public void create(@RequestBody CreateQuestionDto question) {
        questionService.create(question);
    }
}
