package com.tstu.geocoder.model;

import lombok.Data;

import java.util.List;

@Data
public class Result {
    private List<Address> address;
}
