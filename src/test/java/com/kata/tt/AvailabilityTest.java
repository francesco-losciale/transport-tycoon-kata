package com.kata.tt;


import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AvailabilityTest {

    private Availability availability;
    private DeliveryClock deliveryClock;
    private Truck1 truck1;
    private Truck2 truck2;
    private Ship ship;
    private DeliveredItems deliveredItems;

    @Before
    public void setUp() throws Exception {
        deliveryClock = new DeliveryClock();
        availability = new Availability();
        deliveredItems = new DeliveredItems();
        truck1 = new Truck1(availability, deliveredItems);
        truck2 = new Truck2();
        ship = new Ship();
        availability.makeAvailable(truck1);
        availability.makeAvailable(truck2);
        availability.makeAvailable(ship);
    }

    @Test
    public void When_Created_Then_All_The_Transporters_Are_Available() {
        assertThat(availability.isAvailable(truck1)).isTrue();
        assertThat(availability.isAvailable(truck2)).isTrue();
        assertThat(availability.isAvailable(ship)).isTrue();
    }

    @Test
    public void When_Truck1_Is_Unavailable_Then_Only_Other_Transporters_Are_Available() {
        availability.makeUnavailable(truck1);

        assertThat(availability.isAvailable(truck1)).isFalse();
        assertThat(availability.isAvailable(truck2)).isTrue();
        assertThat(availability.isAvailable(ship)).isTrue();
    }

}