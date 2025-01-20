package com.adastra.web.domain.topicModel;

import com.adastra.web.domain.topicModel.enums.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record RecordTopic(

    @NotBlank
    String user_alias,

    @NotBlank
    String title,

    @NotBlank
    String message,

    @NotNull
    Category category
    

) {

}
