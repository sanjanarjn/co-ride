package com.coride.models;


import lombok.Data;
import org.springframework.data.geo.Point;

import java.sql.Timestamp;

@Data
public class RideDto {

    private long id;
    private Point startLocation;
    private Point endLocation;
    private Timestamp startTime;

    private long owner;
}
