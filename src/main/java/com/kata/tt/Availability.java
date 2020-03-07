package com.kata.tt;

import java.util.HashMap;
import java.util.Map;

public class Availability {

    private DeliveryClock clock;
    private Ship ship;
    private Truck2 truck2;
    private Truck1 truck1;
    private Map<Truck1, Integer> instantToFactory = new HashMap<>();

    public Availability(DeliveryClock clock) {
        this.clock = clock;
    }

    public void register(Ship ship) {
        this.ship = ship;
    }

    public void register(Truck2 truck2) {
        this.truck2 = truck2;
    }

    public void register(Truck1 truck1) {
        this.truck1 = truck1;
        this.instantToFactory.put(truck1, 0);
    }

    public Ship getAvailableShip() {
        return this.ship;
    }

    public Truck1 getAvailableTruck1() {
        return (clock.currentInstant() >= this.instantToFactory.get(this.truck1)) ?
            this.truck1 :
                null;
    }

    public Truck2 getAvailableTruck2() {
        return this.truck2;
    }

    public void unavailable(Truck1 truck1) {
        this.instantToFactory.put(truck1, Integer.MAX_VALUE);
    }

    public void unavailable(Truck1 truck1, int instantOfNextAvailability) {
        this.instantToFactory.put(truck1, instantOfNextAvailability);
    }

    public void available(Truck1 truck1) {

    }
}
