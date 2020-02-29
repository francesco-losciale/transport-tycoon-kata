package com.kata.tt;

public class Delivery {

    private Availability availability;
    private DeliveryClock deliveryClock;

    public Delivery(Availability availability, DeliveryClock deliveryClock) {
        this.availability = availability;
        this.deliveryClock = deliveryClock;
    }

    public void process(String deliveryDestination) {
        if (availability.getAvailableTruck1() == null) {
            deliveryClock.tick();
            return; // it will be a loop
        }
        final Truck1 availableTruck1 = availability.getAvailableTruck1();
        availableTruck1.start(deliveryDestination);
    }
}
