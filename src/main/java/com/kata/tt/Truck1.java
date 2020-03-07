package com.kata.tt;

import java.util.Observable;
import java.util.Observer;

import static com.kata.tt.Destination.instantOfArrivalToFactory;
import static com.kata.tt.Destination.instantOfDelivery;

public class Truck1 implements Observer {

    private Availability availability;
    private String name = "Truck1";
    private Integer instantOfCurrentDelivery;
    private Integer instantOfReturnToFactoryAfterDelivered;

    public Truck1(Availability availability) {
        this.availability = availability;
    }

    public Integer start(String shipmentFromFactory) {
        instantOfCurrentDelivery = instantOfDelivery(this, shipmentFromFactory);
        instantOfReturnToFactoryAfterDelivered = instantOfArrivalToFactory(this, shipmentFromFactory);
        availability.makeUnavailable(this);
        return instantOfCurrentDelivery;
    }

    @Override
    public void update(Observable deliveryClock, Object currentInstant) {
        if (currentInstant == instantOfReturnToFactoryAfterDelivered) {
            availability.makeAvailable(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Truck1 truck1 = (Truck1) o;

        if (availability != null ? !availability.equals(truck1.availability) : truck1.availability != null)
            return false;
        return name != null ? name.equals(truck1.name) : truck1.name == null;
    }

    @Override
    public int hashCode() {
        int result = availability != null ? availability.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
