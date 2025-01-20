package com.adastra.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adastra.web.topicModel.RecordTopic;
import com.adastra.web.topicModel.Topic;
import com.adastra.web.topicModel.TopicRepository;
import com.adastra.web.topicModel.UpdateTopicData;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/forum/messages")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;
    
    @PostMapping
    public void registerMessage(@RequestBody RecordTopic topic) {
        System.out.println(topic);
        topicRepository.save(new Topic(topic));
    }

    @GetMapping
    public Page<Topic> topicList(@PageableDefault(size = 3) Pageable pageable) {
        /* return topicRepository.findAll(pageable); */
        return topicRepository.findByIsArchivedFalse(pageable);
    }

    @PutMapping
    @Transactional
    public void updateTopic(@RequestBody @Valid UpdateTopicData updateTopicData) {
        Topic topic = topicRepository.getReferenceById(updateTopicData.id());
        topic.updateData(updateTopicData);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteTopic(@PathVariable Long id) {
        Topic topic = topicRepository.getReferenceById(id);
        topic.archiveTopic();
    }

}
