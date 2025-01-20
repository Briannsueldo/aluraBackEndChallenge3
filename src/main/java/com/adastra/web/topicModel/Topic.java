package com.adastra.web.topicModel;

import java.util.ArrayList;
import java.util.List;

import com.adastra.web.topicModel.enums.Category;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String user_alias;
    private String title;
    private String message;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(name = "is_archived")
    private Boolean isArchived;
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Responses> responses = new ArrayList<>();


    public Topic(RecordTopic topic) {
        this.user_alias = topic.user_alias();
        this.title = topic.title();
        this.message = topic.message();
        this.category = topic.category();
        this.isArchived = false;
    }


    public void updateData(UpdateTopicData updateTopicData) {
        if(updateTopicData.title() != null) {
            this.title = updateTopicData.title();
        }

        if(updateTopicData.message() != null) {
            this.message = updateTopicData.message();
        }

        if(updateTopicData.category() != null) {
            this.category = updateTopicData.category();
        }
    }


    public void archiveTopic() {
        this.isArchived = true;
    }
}
