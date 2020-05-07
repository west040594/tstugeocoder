package com.tstu.geocoder.model;

import lombok.Data;

import java.util.List;

@Data
public class Address {
    private List<Feature> features;
}
