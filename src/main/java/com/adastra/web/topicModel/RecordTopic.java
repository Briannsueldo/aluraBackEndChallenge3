package com.adastra.web.topicModel;

import com.adastra.web.topicModel.enums.Category;

import jakarta.validation.constraints.NotBlank;


public record RecordTopic(

    @NotBlank
    String user_alias,

    @NotBlank
    String title,

    @NotBlank
    String message,

    @NotBlank
    Category category
    

) {

}
