package com.kata.tt;

public class Delivery {

    private final int TIMEOUT_INSTANT = 20;

    private Availability availability;
    private DeliveryClock deliveryClock;

    public Delivery(Availability availability, DeliveryClock deliveryClock) {
        this.availability = availability;
        this.deliveryClock = deliveryClock;
    }

    public void process(String deliveryDestination) {
        while (availability.getAvailableTruck1() == null) {
            throwExceptionIfWaitingTooLong();
            deliveryClock.tick();
        }
        final Truck1 availableTruck1 = availability.getAvailableTruck1();
        availableTruck1.start(deliveryDestination);
    }

    private void throwExceptionIfWaitingTooLong() {
        if (deliveryClock.currentInstant() >= TIMEOUT_INSTANT) {
            throw new RuntimeException("Too long waiting");
        }
    }
}
