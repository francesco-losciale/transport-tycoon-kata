package com.kata.tt;

public class Delivery {

    private final int TIMEOUT_INSTANT = 1000;

    private Availability availability;
    private DeliveryClock deliveryClock;
    private DeliveredItems deliveredItems;

    public Delivery(Availability availability, DeliveryClock deliveryClock, DeliveredItems deliveredItems) {
        this.availability = availability;
        this.deliveryClock = deliveryClock;
        this.deliveredItems = deliveredItems;
    }

    public void process(String deliveryDestination) {
        while (availability.getAvailableTruck1() == null) {
            deliveryClock.tick();
            throwExceptionIfWaitingTooLong();
        }
        final Truck1 truck1 = availability.getAvailableTruck1();
        final Integer instantOfDelivery = truck1.start(deliveryDestination);
        while (deliveryClock.currentInstant() < instantOfDelivery) {
            deliveryClock.tick();
            throwExceptionIfWaitingTooLong();
        }
        deliveredItems.store(deliveryDestination, instantOfDelivery);
    }

    private void throwExceptionIfWaitingTooLong() {
        if (deliveryClock.currentInstant() >= TIMEOUT_INSTANT) {
            throw new RuntimeException("Too long waiting");
        }
    }
}
