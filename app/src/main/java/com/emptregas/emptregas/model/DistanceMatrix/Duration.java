package com.emptregas.emptregas.model.DistanceMatrix;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Duration {
    @JsonProperty("text")
    private String text;
    @JsonProperty("value")
    private Integer value;

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("value")
    public Integer getValue() {
        return value;
    }

   /* public Duration(String text, int value) {
        this.text = text;
        this.value = value;
    }*/
}