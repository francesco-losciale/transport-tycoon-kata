package com.kata.tt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeliveryTest {

    @InjectMocks
    private Delivery delivery;

    @Mock
    private Availability availability;

    @Mock
    private DeliveryClock clock;

    @Test
    public void When_Transporters_Available_Then_Do_Not_Tick_Clock() {
        when(availability.getAvailableTruck1()).thenReturn(new Truck1());

        delivery.process("delivery-name");

        verifyZeroInteractions(clock);
    }

    @Test
    public void When_Transporters_Not_Available_Then_Do_Tick_Clock() {
        when(availability.getAvailableTruck1()).thenReturn(null);

        delivery.process("delivery-name");

        verify(clock).tick();
    }

    @Test
    public void When_New_Delivery_Then_Instruct_Truck() {
        final Truck1 truck1 = Mockito.mock(Truck1.class);
        when(availability.getAvailableTruck1()).thenReturn(truck1);
        String shipmentFromFactory = "A";

        delivery.process(shipmentFromFactory);

        verify(truck1).start(shipmentFromFactory, availability);
    }
}