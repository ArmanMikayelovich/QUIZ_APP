package com.workfront.quiz.dto.topic;

import com.workfront.quiz.entity.TopicEntity;
import lombok.Data;

@Data
public class TopicOnlyTitleDto {

    private String title;

    public TopicEntity toEntity(){
        TopicEntity topic = new TopicEntity();

        topic.setTitle(this.title);

        return topic;
    }
}
