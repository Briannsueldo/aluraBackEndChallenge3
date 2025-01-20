package com.adastra.web.domain.topicModel;

import com.adastra.web.domain.topicModel.enums.Category;

public record RegisterTopicData(

    Long id,
    String user_alias,
    String title,
    String message,
    Category category

) {
    
}
