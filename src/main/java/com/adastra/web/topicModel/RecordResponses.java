package com.adastra.web.topicModel;

import jakarta.validation.constraints.NotBlank;

public record RecordResponses(

    @NotBlank
    String user_alias,

    @NotBlank
    String response
    
) {

}
