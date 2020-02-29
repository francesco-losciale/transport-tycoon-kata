package com.kata.tt;

import static com.kata.tt.Destination.instantOfArrivalToFactory;
import static com.kata.tt.Destination.instantOfDelivery;

public class Truck1 {

    private Availability availability;
    private String name = "Truck1";

    public Truck1(Availability availability) {
        this.availability = availability;
    }

    public void start(String shipmentFromFactory) {
        instantOfDelivery(this, shipmentFromFactory);
        final Integer instantOfArrivalToFactory = instantOfArrivalToFactory(this, shipmentFromFactory);
        availability.unavailable(this, instantOfArrivalToFactory);
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
