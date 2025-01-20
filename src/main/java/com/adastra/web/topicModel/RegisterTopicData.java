package com.adastra.web.topicModel;

import com.adastra.web.topicModel.enums.Category;

public record RegisterTopicData(

    Long id,
    String user_alias,
    String title,
    String message,
    Category category

) {
    
}
