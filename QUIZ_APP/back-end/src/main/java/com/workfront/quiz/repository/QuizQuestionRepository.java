package com.workfront.quiz.repository;

import com.workfront.quiz.entity.QuizQuestionEntity;
import org.springframework.data.repository.CrudRepository;

public interface QuizQuestionRepository extends CrudRepository<QuizQuestionEntity, Long> {

}
