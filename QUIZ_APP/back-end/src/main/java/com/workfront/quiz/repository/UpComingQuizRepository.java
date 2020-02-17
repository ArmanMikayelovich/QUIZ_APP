package com.workfront.quiz.repository;

import com.workfront.quiz.entity.UpcomingQuizEntity;
import com.workfront.quiz.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UpComingQuizRepository extends JpaRepository<UpcomingQuizEntity, Long> {
    Page<UpcomingQuizEntity> findAllByUser(UserEntity userEntity, Pageable pageable);
}
