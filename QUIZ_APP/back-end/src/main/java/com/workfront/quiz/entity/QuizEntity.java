package com.workfront.quiz.entity;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "quizes")
public class QuizEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TopicEntity topic;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quiz")
    private List<QuizQuestionEntity> quizQuestions;

    @CreatedDate
    @Column(name = "start_time", updatable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "expected_duration")
    private Integer duration;

    @Column(name = "success_percent", updatable = false, scale = 2, precision = 5)
    private Double successPercent;

    @Column(name = "is_finished", nullable = false)
    private Boolean isFinished = Boolean.FALSE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuizEntity)) return false;
        QuizEntity that = (QuizEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getStartTime(), that.getStartTime()) &&
                Objects.equals(getEndTime(), that.getEndTime()) &&
                Objects.equals(getDuration(), that.getDuration());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStartTime(), getEndTime(), getDuration());
    }

    @Override
    public String toString() {
        return "QuizEntity{" +
                "id=" + id +
                ", user=" + user.getId() +
                ", topic=" + topic.getId() +
                ", quizQuestions=" + quizQuestions +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration=" + duration +
                ", successPercent=" + successPercent +
                '}';
    }
}
