package com.workfront.quiz.api;

import com.workfront.quiz.dto.topic.TopicDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TopicController {

    TopicDto findById(Long id);

    Page<TopicDto> searchByTitle(String title, Pageable pageable);

    Page<TopicDto> getAllTopics(Pageable pageable);

    void remove(Long id);

    void update(TopicDto topic);
}
