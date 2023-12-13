package com.coride.models;

import lombok.Data;

import java.util.List;

@Data
public class RideMatchResponse {

    private List<RideMatch> matchingRides;
}
