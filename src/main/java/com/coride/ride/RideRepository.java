package com.coride.ride;

import com.coride.models.RideOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<RideOffer, Long> {
}
