package com.adastra.web.domain.topicModel;

import jakarta.validation.constraints.NotBlank;

public record RecordResponses(

    @NotBlank
    String user_alias,

    @NotBlank
    String response
    
) {

}
