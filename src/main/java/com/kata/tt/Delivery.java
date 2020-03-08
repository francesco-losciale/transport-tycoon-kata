package com.kata.tt;

public class Delivery {

    private final int TIMEOUT_INSTANT = 1000;

    private Availability availability;
    private DeliveryClock deliveryClock;
    private Truck truck;

    public Delivery(Availability availability, DeliveryClock deliveryClock, Truck truck) {
        this.availability = availability;
        this.deliveryClock = deliveryClock;
        this.truck = truck;
        this.availability.makeAvailable(truck);
        this.deliveryClock.addObserver(truck);
    }

    public void process(String deliveryDestination) {
        while (!availability.isAvailable(truck)) {
            deliveryClock.tick();
            throwExceptionIfWaitingTooLong();
        }
        truck.start(deliveryDestination);
        while (!availability.isAvailable(truck)) {
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
