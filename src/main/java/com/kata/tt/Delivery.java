package com.kata.tt;

public class Delivery {

    private Availability availability;
    private DeliveryClock deliveryClock;

    public Delivery(Availability availability, DeliveryClock deliveryClock) {
        this.availability = availability;
        this.deliveryClock = deliveryClock;
    }

    public void process(String deliveryName) {
        if (availability.getAvailableTruck1() == null) {
            deliveryClock.tick();
        }
    }
}
