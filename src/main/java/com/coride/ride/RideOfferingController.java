package com.coride.ride;

import com.coride.models.RideDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offer")
public class RideOfferingController {

    @Autowired
    private RideService rideService;

    @PostMapping
    public ResponseEntity<RideDto> createRide(@RequestBody RideDto rideDto) {
        RideDto savedRide = rideService.createNewRide(rideDto);
        return new ResponseEntity<>(savedRide, HttpStatus.CREATED);
    }
}
