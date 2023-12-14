package com.coride.ride;

import com.coride.models.*;
import org.jetbrains.annotations.NotNull;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        return getRideDto(ride);
    }

    @NotNull
    private RideDto getRideDto(RideOffer ride) {

        RideDto savedRideDto = modelMapper.map(ride, RideDto.class);

        Coordinate startLocation = ride.getStartLocation().getCoordinate();
        savedRideDto.setStartLocation(new Point(startLocation.getX(), startLocation.getY()));
        Coordinate endLocation = ride.getEndLocation().getCoordinate();
        savedRideDto.setEndLocation(new Point(endLocation.getX(), endLocation.getY()));

        return savedRideDto;
    }

    public RideMatchResponse getMatchingRides(RideDto rideDto) {

        Coordinate startCoordinate = new Coordinate(rideDto.getStartLocation().getX(), rideDto.getStartLocation().getY());
        Coordinate endCoordinate = new Coordinate(rideDto.getEndLocation().getX(), rideDto.getEndLocation().getY());

        List<RideOffer> matchingRides = rideRepository.findRidesWithinDistance(geometryFactory.createPoint(startCoordinate), geometryFactory.createPoint(endCoordinate), 50);
        List<RideMatch> matches = new ArrayList<>();
        for(RideOffer rideOffer: matchingRides) {
            RideMatch rideMatch = new RideMatch();
            rideMatch.setRideDto(getRideDto(rideOffer));
            matches.add(rideMatch);
        }
        RideMatchResponse rideMatchResponse = new RideMatchResponse();
        rideMatchResponse.setMatchingRides(matches);

        return rideMatchResponse;
    }
}
