package com.coride.models;


import jakarta.persistence.*;
import lombok.Data;
import org.locationtech.jts.geom.Geometry;

import java.sql.Timestamp;

@Data
@MappedSuperclass
public abstract class Ride {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_location")
    private Geometry startLocation;

    @Column(name = "end_location")
    private Geometry endLocation;

    private long owner;

    @Column(name = "start_time")
    private Timestamp startTime;
}
