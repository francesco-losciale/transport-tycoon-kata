package com.kata.tt;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class Truck1Test {

    @InjectMocks
    private Truck1 truck1;

    @Mock
    private Availability availability;

    @Test
    public void When_Truck1_Starts_Then_Unavailable_For_Other_Deliveries() {
        String deliveryDestination = "test";
        truck1.start(deliveryDestination, availability);

        verify(availability).unavailable(truck1);
    }
}