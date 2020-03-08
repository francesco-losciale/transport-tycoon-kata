package com.kata.tt;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Availability {

    private Map<Object, Boolean> transporterAvailabilityMap = new HashMap<>();

    public void makeAvailable(Object transporter) {
        this.transporterAvailabilityMap.put(transporter, TRUE);
    }

    public void makeUnavailable(Truck truck) {
        this.transporterAvailabilityMap.put(truck, FALSE);
    }

    public boolean isAvailable(Object transporter) {
        return transporterAvailabilityMap.get(transporter);
    }
}
