package com.kata.tt;

import java.util.Observable;
import java.util.Observer;

import static com.kata.tt.Destination.instantOfArrivalToFactory;
import static com.kata.tt.Destination.instantOfDelivery;

public class Truck implements Observer {

    private Availability availability;
    private String name = "Truck1";
    private Integer instantOfCurrentDelivery;
    private Integer instantOfReturnToFactoryAfterDelivered;
    private String destination;
    private DeliveredItems deliveredItems;

    public Truck(Availability availability, DeliveredItems deliveredItems) {
        this.availability = availability;
        this.deliveredItems = deliveredItems;
    }

    public void start(String destination) {
        this.instantOfCurrentDelivery = instantOfDelivery(this, destination);
        this.instantOfReturnToFactoryAfterDelivered = instantOfArrivalToFactory(this, destination);
        this.destination = destination;
        this.availability.makeUnavailable(this);
    }

    @Override
    public void update(Observable deliveryClock, Object currentInstant) {
        if (currentInstant == instantOfReturnToFactoryAfterDelivered) {
            availability.makeAvailable(this);
        }
        if (currentInstant == instantOfCurrentDelivery) {
            deliveredItems.store(destination, (Integer) currentInstant);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Truck truck = (Truck) o;

        if (availability != null ? !availability.equals(truck.availability) : truck.availability != null)
            return false;
        return name != null ? name.equals(truck.name) : truck.name == null;
    }

    @Override
    public int hashCode() {
        int result = availability != null ? availability.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
