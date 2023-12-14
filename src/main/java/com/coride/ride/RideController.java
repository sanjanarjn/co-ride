package com.coride.ride;

import com.coride.models.RideDto;
import com.coride.models.RideMatchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rides")
public class RideController {

    @Autowired
    private RideService rideService;

    @PostMapping("/offer")
    public ResponseEntity<RideDto> createRide(@RequestBody RideDto rideDto) {
        RideDto savedRide = rideService.createNewRide(rideDto);
        return new ResponseEntity<>(savedRide, HttpStatus.CREATED);
    }

    @PostMapping("/search")
    public ResponseEntity<RideMatchResponse> searchRides(@RequestBody RideDto rideDto) {
        RideMatchResponse rideMatchResponse = rideService.getMatchingRides(rideDto);
        return new ResponseEntity<>(rideMatchResponse, HttpStatus.OK);
    }
}
