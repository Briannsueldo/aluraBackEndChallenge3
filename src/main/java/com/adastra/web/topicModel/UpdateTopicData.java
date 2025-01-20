package com.adastra.web.topicModel;

import com.adastra.web.topicModel.enums.Category;

import jakarta.validation.constraints.NotNull;

public record UpdateTopicData(

    @NotNull
    Long id,
    String title,
    String message,
    Category category
) {
    
}
