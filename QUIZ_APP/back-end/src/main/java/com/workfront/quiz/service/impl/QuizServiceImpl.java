package com.workfront.quiz.service.impl;

import com.workfront.quiz.dto.question.QuestionDto;
import com.workfront.quiz.dto.quiz.QuizDto;
import com.workfront.quiz.entity.*;
import com.workfront.quiz.repository.QuizQuestionRepository;
import com.workfront.quiz.repository.QuizRepository;
import com.workfront.quiz.repository.UpComingQuizRepository;
import com.workfront.quiz.repository.UserRepository;
import com.workfront.quiz.service.QuestionService;
import com.workfront.quiz.service.QuizService;
import com.workfront.quiz.service.UserService;
import com.workfront.quiz.service.util.exception.QuizNotFoundException;
import com.workfront.quiz.service.util.exception.UpcomingQuizNotFoundException;
import com.workfront.quiz.service.util.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class QuizServiceImpl implements QuizService {
    private UserService userService;

    private QuizRepository quizRepository;

    private QuestionService questionService;

    private UpComingQuizRepository upComingQuizRepository;
    private UserRepository userRepository;
    private QuizQuestionRepository quizQuestionRepository;

    public QuizServiceImpl(UserService userService, QuizRepository quizRepository, QuestionService questionService,
                           UpComingQuizRepository upComingQuizRepository, UserRepository userRepository,
                           QuizQuestionRepository quizQuestionRepository) {
        this.userService = userService;
        this.quizRepository = quizRepository;
        this.questionService = questionService;
        this.upComingQuizRepository = upComingQuizRepository;
        this.userRepository = userRepository;
        this.quizQuestionRepository = quizQuestionRepository;
    }

    @Override
    public QuizDto findById(Long id) {
        QuizEntity quizEntity = quizRepository.findById(id).orElseThrow(() -> new QuizNotFoundException(id));
        return QuizDto.mapFromEntity(quizEntity);

    }

    @Override
    public Page<QuizDto> getAllQuizes(Pageable pageable) {
        Page<QuizEntity> quizes = quizRepository.findAll(pageable);
        return quizes.map(QuizDto::mapFromEntity);
    }

    @Override
    public Page<QuizDto> getQuizesByTopic(TopicEntity topic, Pageable pageable) {
        Page<QuizEntity> quizes = quizRepository.findAllByTopic(topic, pageable);
        return quizes.map(QuizDto::mapFromEntity);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        quizRepository.findById(id).orElseThrow(() -> new QuizNotFoundException(id));
        quizRepository.deleteById(id);
    }


    @Override
    @Transactional
    public Collection<QuestionDto> generateQuiz(Long upComingQuizId) {

        UpcomingQuizEntity upcomingQuizEntity = upComingQuizRepository.findById(upComingQuizId)
                .orElseThrow(() -> new UpcomingQuizNotFoundException(upComingQuizId));

        List<QuestionEntity> questionEntities = questionService
                .generateQuestions(upcomingQuizEntity.getTopic().getId(),upcomingQuizEntity.getCount());

        Long userId = userService.getMe();

        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId));

        QuizEntity quizEntity = new QuizEntity();
        quizEntity.setUser(userEntity);
        quizEntity.setDuration(upcomingQuizEntity.getDurationInMinutes());
        quizEntity.setTopic(upcomingQuizEntity.getTopic());

        quizRepository.save(quizEntity);

        for (QuestionEntity questionEntity : questionEntities) {
            QuizQuestionEntity quizQuestionEntity = new QuizQuestionEntity();
            quizQuestionEntity.setQuiz(quizEntity);
            quizQuestionEntity.setQuestion(questionEntity);
            quizQuestionRepository.save(quizQuestionEntity);
        }

        return null;
    }
}
