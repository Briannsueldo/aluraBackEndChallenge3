package com.adastra.web.topicModel;

import com.adastra.web.topicModel.enums.Category;

import jakarta.validation.constraints.NotNull;

public record UpdateTopicData(

    @NotNull
    Long id,
    String user_alias,
    String title,
    String message,
    Category category

) {
    
}
