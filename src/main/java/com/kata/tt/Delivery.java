package com.kata.tt;

public class Delivery {

    private final int TIMEOUT_INSTANT = 1000;

    private Availability availability;
    private DeliveryClock deliveryClock;
    private DeliveredItems deliveredItems;
    private Truck1 truck1;

    public Delivery(Availability availability, DeliveryClock deliveryClock, DeliveredItems deliveredItems, Truck1 truck1) {
        this.availability = availability;
        this.deliveryClock = deliveryClock;
        this.deliveredItems = deliveredItems;
        this.truck1 = truck1;
    }

    public void process(String deliveryDestination) {
        availability.makeAvailable(truck1);
        while (!availability.isAvailable(truck1)) {
            deliveryClock.tick();
            throwExceptionIfWaitingTooLong();
        }
        final Integer instantOfDelivery = truck1.start(deliveryDestination);
        while (deliveryClock.currentInstant() < instantOfDelivery) {
            deliveryClock.tick();
            throwExceptionIfWaitingTooLong();
        }
        deliveredItems.store(deliveryDestination, instantOfDelivery);
    }

    private void throwExceptionIfWaitingTooLong() {
        if (deliveryClock.currentInstant() >= TIMEOUT_INSTANT) {
            throw new RuntimeException("Waiting too long for available trucks");
        }
    }
}
