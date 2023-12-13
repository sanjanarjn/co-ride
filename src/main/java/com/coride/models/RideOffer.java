package com.coride.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ride_offer")
public class RideOffer extends Ride {

}
