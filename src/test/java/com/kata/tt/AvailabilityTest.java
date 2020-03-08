package com.kata.tt;


import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AvailabilityTest {

    private Availability availability;
    private DeliveryClock deliveryClock;
    private Truck truck;
    private Truck2 truck2;
    private Ship ship;
    private DeliveredItems deliveredItems;

    @Before
    public void setUp() throws Exception {
        deliveryClock = new DeliveryClock();
        availability = new Availability();
        deliveredItems = new DeliveredItems();
        truck = new Truck(availability, deliveredItems);
        truck2 = new Truck2();
        ship = new Ship();
        availability.makeAvailable(truck);
        availability.makeAvailable(truck2);
        availability.makeAvailable(ship);
    }

    @Test
    public void When_Created_Then_All_The_Transporters_Are_Available() {
        assertThat(availability.isAvailable(truck)).isTrue();
        assertThat(availability.isAvailable(truck2)).isTrue();
        assertThat(availability.isAvailable(ship)).isTrue();
    }

    @Test
    public void When_Truck1_Is_Unavailable_Then_Only_Other_Transporters_Are_Available() {
        availability.makeUnavailable(truck);

        assertThat(availability.isAvailable(truck)).isFalse();
        assertThat(availability.isAvailable(truck2)).isTrue();
        assertThat(availability.isAvailable(ship)).isTrue();
    }

}