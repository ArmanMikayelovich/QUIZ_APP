package com.workfront.quiz.api;

import com.workfront.quiz.dto.question.CreateQuestionDto;
import com.workfront.quiz.dto.topic.TopicDto;
import com.workfront.quiz.dto.topic.TopicOnlyTitleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface TopicController {


    void create(TopicOnlyTitleDto topicDto);

    TopicDto findById(Long id);

    Page<TopicDto> searchByTitle(String title, Pageable pageable);

    Page<TopicDto> getAllTopics(Pageable pageable);

    void remove(Long id);

    void update(TopicDto topic);
}
