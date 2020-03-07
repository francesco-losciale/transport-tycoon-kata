package com.kata.tt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeliveryTest {

    @InjectMocks
    private Delivery delivery;

    @Mock
    private Availability availability;

    @Mock
    private DeliveryClock clock;

    @Mock
    private Truck1 truck1;

    @Mock
    private DeliveredItems deliveredItems;

    @Test(expected = RuntimeException.class)
    public void When_Transporters_Not_Available_Then_System_Error_At_Instant_1000() {
        when(availability.isAvailable(any())).thenReturn(false);
        when(clock.currentInstant()).thenReturn(1000);

        delivery.process("delivery-name");
    }

    @Test
    public void When_New_Delivery_Then_Instruct_Truck() {
        when(availability.isAvailable(any())).thenReturn(true);

        delivery.process("A");

        verify(truck1).start("A");
    }

    @Test
    public void When_Delivery_Completed_Then_DeliveryItem_Has_Been_Stored() {
        when(availability.isAvailable(any())).thenReturn(true);
        when(truck1.start("B")).thenReturn(5);
        when(clock.currentInstant()).thenReturn(5);

        delivery.process("B");

        verify(deliveredItems).store("B", 5);
    }
}
