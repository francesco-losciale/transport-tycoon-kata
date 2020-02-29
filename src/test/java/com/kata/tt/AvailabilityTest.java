package com.kata.tt;


import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AvailabilityTest {

    private Availability availability;
    private DeliveryClock deliveryClock;

    @Before
    public void setUp() throws Exception {
        deliveryClock = new DeliveryClock();
        availability = new Availability(deliveryClock);
        availability.register(new Truck1(availability));
        availability.register(new Truck2());
        availability.register(new Ship());
    }

    @Test
    public void When_Created_Then_All_The_Transporters_Are_Available() {
        assertThat(availability.getAvailableTruck1()).isNotNull();
        assertThat(availability.getAvailableTruck2()).isNotNull();
        assertThat(availability.getAvailableShip()).isNotNull();
    }

    @Test
    public void When_Truck1_Is_Unavailable_Then_Only_Other_Transporters_Are_Available() {
        Truck1 truck1 = new Truck1(availability);

        availability.unavailable(truck1);

        assertThat(availability.getAvailableTruck1()).isNull();
        assertThat(availability.getAvailableTruck2()).isNotNull();
        assertThat(availability.getAvailableShip()).isNotNull();
    }

    @Test
    public void When_Truck1_Is_Unavailable_Then_Becomes_Available_After_1_Tick() {
        Truck1 truck1 = new Truck1(availability);

        availability.unavailable(truck1, 1);
        deliveryClock.tick();

        assertThat(availability.getAvailableTruck1()).isNotNull();
    }

    @Test
    public void When_Truck1_Is_Unavailable_For_2_Then_Is_NotAvailable_After_1_Tick() {
        Truck1 truck1 = new Truck1(availability);

        availability.unavailable(truck1, 2);
        deliveryClock.tick();

        assertThat(availability.getAvailableTruck1()).isNull();
    }


    @Test
    public void When_Truck1_Is_Unavailable_For_2_Then_Becomes_Available_After_2_Tick() {
        Truck1 truck1 = new Truck1(availability);

        availability.unavailable(truck1, 2);
        deliveryClock.tick();
        deliveryClock.tick();

        assertThat(availability.getAvailableTruck1()).isNotNull();
    }
}