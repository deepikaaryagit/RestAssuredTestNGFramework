
package com.spotify.oauth2.pojo;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
@Getter @Setter 
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "error"
})

public class Error {

    @JsonProperty("error")
    private InnerError error;

  

}
