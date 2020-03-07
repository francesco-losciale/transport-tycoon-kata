package com.kata.tt;

public class Delivery {

    private final int TIMEOUT_INSTANT = 1000;

    private Availability availability;
    private DeliveryClock deliveryClock;
    private Truck1 truck1;

    public Delivery(Availability availability, DeliveryClock deliveryClock, Truck1 truck1) {
        this.availability = availability;
        this.deliveryClock = deliveryClock;
        this.truck1 = truck1;
        this.availability.makeAvailable(truck1);
        this.deliveryClock.addObserver(truck1);
    }

    public void process(String deliveryDestination) {
        while (!availability.isAvailable(truck1)) {
            deliveryClock.tick();
            throwExceptionIfWaitingTooLong();
        }
        truck1.start(deliveryDestination);
        while (!availability.isAvailable(truck1)) {
            deliveryClock.tick();
            throwExceptionIfWaitingTooLong();
        }
    }

    private void throwExceptionIfWaitingTooLong() {
        if (deliveryClock.currentInstant() >= TIMEOUT_INSTANT) {
            throw new RuntimeException("Waiting too long for available trucks");
        }
    }
}
