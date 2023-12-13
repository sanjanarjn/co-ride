package com.coride.ride;

import com.coride.models.Ride;
import com.coride.models.RideDto;
import com.coride.models.RideMatchResponse;
import com.coride.models.RideOffer;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GeometryFactory geometryFactory;

    public RideDto createNewRide(RideDto rideDto) {

        RideOffer ride = modelMapper.map(rideDto, RideOffer.class);
        Point startLocation = rideDto.getStartLocation();
        ride.setStartLocation(geometryFactory.createPoint(new Coordinate(startLocation.getX(), startLocation.getY())));
        Point endLocation = rideDto.getEndLocation();
        ride.setEndLocation(geometryFactory.createPoint(new Coordinate(endLocation.getX(), endLocation.getY())));

        ride = rideRepository.save(ride);

        RideDto savedRideDto = modelMapper.map(ride, RideDto.class);
        savedRideDto.setStartLocation(new Point(startLocation.getX(), startLocation.getY()));
        savedRideDto.setEndLocation(new Point(endLocation.getX(), endLocation.getY()));

        return savedRideDto;
    }
}
