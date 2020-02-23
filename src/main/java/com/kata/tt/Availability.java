package com.kata.tt;

public class Availability {

    private Ship ship;
    private Truck2 truck2;
    private Truck1 truck1;

    public void register(Ship ship) {
        this.ship = ship;
    }

    public void register(Truck2 truck2) {
        this.truck2 = truck2;
    }

    public void register(Truck1 truck1) {
        this.truck1 = truck1;
    }

    public Ship getAvailableShip() {
        return this.ship;
    }

    public Truck1 getAvailableTruck1() {
        return this.truck1;
    }

    public Truck2 getAvailableTruck2() {
        return this.truck2;
    }

    public void unavailable(Truck1 truck1) {
        this.truck1 = null;
    }
}
