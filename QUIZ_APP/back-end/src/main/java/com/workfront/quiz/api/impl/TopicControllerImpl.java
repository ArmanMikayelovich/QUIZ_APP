package com.workfront.quiz.api.impl;

import com.workfront.quiz.api.TopicController;
import com.workfront.quiz.dto.topic.TopicDto;
import com.workfront.quiz.dto.topic.TopicOnlyTitleDto;
import com.workfront.quiz.service.TopicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/topic/")
@CrossOrigin(value = "*")
public class TopicControllerImpl implements TopicController {

    private TopicService topicService;

    public TopicControllerImpl(TopicService topicService) {
        this.topicService = topicService;
    }

    @Override
    @PostMapping("create")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public void create(@RequestBody TopicOnlyTitleDto topicDto) {
        topicService.create(topicDto);
    }

    @Override
    @GetMapping("{id}")
    public TopicDto findById(@PathVariable Long id) {
        return topicService.findById(id);
    }

    @Override
    @GetMapping("search")
    public Page<TopicDto> searchByTitle(String title, @PageableDefault Pageable pageable) {
        return topicService.searchByTitle(title, pageable);
    }

    @Override
    @GetMapping("all")
    public Page<TopicDto> getAllTopics(@PageableDefault Pageable pageable) {
        return topicService.getAllTopics(pageable);
    }

    @Override
    @DeleteMapping("{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public void remove(@PathVariable Long id) {
        topicService.remove(id);
    }

    @Override
    @PutMapping("update")
    public void update(TopicDto topic) {
        topicService.update(topic);
    }
}
