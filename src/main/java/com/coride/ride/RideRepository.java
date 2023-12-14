package com.coride.ride;

import com.coride.models.Ride;
import com.coride.models.RideOffer;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RideRepository extends JpaRepository<RideOffer, Long> {

    @Query("SELECT r FROM RideOffer r " +
            "WHERE ST_Distance(r.startLocation, :inputStart) <= :distanceThreshold " +
            "AND ST_Distance(r.endLocation, :inputEnd) <= :distanceThreshold")
    List<RideOffer> findRidesWithinDistance(
            @Param("inputStart") Geometry inputStart,
            @Param("inputEnd") Geometry inputEnd,
            @Param("distanceThreshold") double distanceThreshold);
}
