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
        Truck transporter = availableTruckNow();
        transporter.start(deliveryDestination);
        while (!availability.isAvailable(transporter)) {
            deliveryClock.tick();
            throwExceptionIfWaitingTooLong();
        }
    }

    private Truck availableTruckNow() {
        Truck transporter = null;
        while (!availability.isAvailable(truck)) {
            deliveryClock.tick();
            throwExceptionIfWaitingTooLong();
        }
        transporter = truck;
        return transporter;
    }

    private void throwExceptionIfWaitingTooLong() {
        if (deliveryClock.currentInstant() >= TIMEOUT_INSTANT) {
            throw new RuntimeException("Waiting too long for available trucks");
        }
    }
}
