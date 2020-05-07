package com.tstu.geocoder.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Properties {
    @JsonProperty("display_name")
    private String displayName;
}
