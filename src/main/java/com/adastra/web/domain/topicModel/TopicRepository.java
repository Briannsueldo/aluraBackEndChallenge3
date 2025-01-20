package com.adastra.web.domain.topicModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    Page<Topic> findByIsArchivedFalse(Pageable pageable);
    
}
