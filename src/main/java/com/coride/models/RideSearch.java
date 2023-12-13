package com.coride.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "ride_offer")
public class RideSearch extends Ride {

}
