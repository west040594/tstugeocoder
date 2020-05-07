package com.tstu.geocoder.model;

import lombok.Data;

@Data
public class Feature {
    private GeometryWrapper geometry;
    private Properties properties;
}
