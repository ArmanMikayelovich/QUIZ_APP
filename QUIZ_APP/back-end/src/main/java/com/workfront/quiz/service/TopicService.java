package com.workfront.quiz.service;

import com.workfront.quiz.dto.topic.TopicDto;
import com.workfront.quiz.entity.TopicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TopicService {

    TopicDto findById(Long id);

    Page<TopicDto> searchByTitle(String title, Pageable pageable);

    Page<TopicDto> getAllTopics(Pageable pageable);

    void remove(Long id);

    void update(TopicDto topic);
}
