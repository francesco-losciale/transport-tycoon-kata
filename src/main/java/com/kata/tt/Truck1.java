package com.kata.tt;

import static com.kata.tt.Destination.instantOfArrivalToFactory;
import static com.kata.tt.Destination.instantOfDelivery;

public class Truck1 {

    public void start(String shipmentFromFactory, Availability availability) {
        instantOfDelivery(this, shipmentFromFactory);
        final Integer instantOfArrivalToFactory = instantOfArrivalToFactory(this, shipmentFromFactory);
        availability.unavailable(this, instantOfArrivalToFactory);
    }

}
