package com.kata.tt;

public class Truck1 {

    public void start(String shipmentFromFactory, Availability availability) {
        availability.unavailable(this);
    }

}
