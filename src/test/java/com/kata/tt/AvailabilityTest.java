package com.kata.tt;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

public class AvailabilityTest {

    private Availability availability = new Availability();

    @Before
    public void setUp() throws Exception {
        availability.register(new Truck1());
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
        Truck1 truck1 = new Truck1();

        availability.unavailable(truck1);

        assertThat(availability.getAvailableTruck1()).isNull();
        assertThat(availability.getAvailableTruck2()).isNotNull();
        assertThat(availability.getAvailableShip()).isNotNull();
    }
}