package com.workfront.quiz.repository;

import com.workfront.quiz.entity.UpcomingQuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

@Repository
public interface UpComingQuizRepository extends JpaRepository<UpcomingQuizEntity, Long> {

}
