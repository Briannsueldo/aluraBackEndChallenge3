package com.adastra.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.adastra.web.domain.topicModel.RecordTopic;
import com.adastra.web.domain.topicModel.RegisterTopicData;
import com.adastra.web.domain.topicModel.Topic;
import com.adastra.web.domain.topicModel.TopicRepository;
import com.adastra.web.domain.topicModel.UpdateTopicData;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/forum/messages")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;
    
    @PostMapping
    public ResponseEntity<RegisterTopicData> registerMessage(@RequestBody @Valid RecordTopic topic, UriComponentsBuilder uriComponentsBuilder) {
        System.out.println(topic);
        Topic newTopic = topicRepository.save(new Topic(topic));
        RegisterTopicData registerTopicData = new RegisterTopicData(newTopic.getId(), newTopic.getUser_alias(), newTopic.getTitle(), newTopic.getMessage(), newTopic.getCategory());

        URI url = uriComponentsBuilder.path("/forum/messages/{id}").buildAndExpand(newTopic.getId()).toUri();
        return ResponseEntity.created(url).body(registerTopicData);

    }

    @GetMapping
    public ResponseEntity<Page<Topic>> topicList(@PageableDefault(size = 3) Pageable pageable) {
        return ResponseEntity.ok(topicRepository.findByIsArchivedFalse(pageable));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<UpdateTopicData> updateTopic(@RequestBody @Valid UpdateTopicData updateTopicData) {
        Topic topic = topicRepository.getReferenceById(updateTopicData.id());
        topic.updateData(updateTopicData);
        return ResponseEntity.ok(new UpdateTopicData(topic.getId(), topic.getUser_alias(), topic.getTitle(), topic.getMessage(), topic.getCategory()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable Long id) {
        Topic topic = topicRepository.getReferenceById(id);
        topic.archiveTopic();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<RegisterTopicData> returnTopicData(@PathVariable Long id) {
        Topic topic = topicRepository.getReferenceById(id);
        topic.getId();
        var topicData = new RegisterTopicData(topic.getId(), topic.getUser_alias(), topic.getTitle(), topic.getMessage(), topic.getCategory());
        return ResponseEntity.ok(topicData);
    }

}
