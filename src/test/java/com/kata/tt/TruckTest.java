package com.kata.tt;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Destination.class)
public class TruckTest {

    @InjectMocks
    private Truck truck;

    @Mock
    private Availability availability;

    @Mock
    private DeliveredItems deliveredItems;

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(Destination.class);
    }

    @Test
    public void When_Truck1_Starts_Then_Ask_Destination_To_Know_How_Long_It_Would_Take() {
        String deliveryDestination = "test";
        truck.start(deliveryDestination);

        PowerMockito.verifyStatic(Destination.class);
        Destination.instantOfDelivery(truck, deliveryDestination);
        Destination.instantOfArrivalToFactory(truck, deliveryDestination);
    }

    @Test
    public void When_Truck1_Starts_Then_Unavailable_For_Other_Deliveries() {
        String deliveryDestination = "test";
        PowerMockito.mockStatic(Destination.class);
        BDDMockito.given(Destination.instantOfArrivalToFactory(truck, deliveryDestination)).willReturn(0);

        truck.start(deliveryDestination);
        truck.update(Mockito.mock(DeliveryClock.class), 1);

        verify(availability).makeUnavailable(truck);
        verify(availability, never()).makeAvailable(truck);
    }

    @Test
    public void When_Truck1_Starts_Then_Availability_Receives_Time_Truck1_Will_Be_Available_Again() {
        String deliveryDestination = "test";
        PowerMockito.mockStatic(Destination.class);
        BDDMockito.given(Destination.instantOfArrivalToFactory(truck, deliveryDestination)).willReturn(5);

        truck.start(deliveryDestination);
        truck.update(Mockito.mock(DeliveryClock.class), 5);

        verify(availability).makeAvailable(truck);
    }

    @Test
    public void When_Truck1_Starts_Then_Check_Instant_Of_Delivery() {
        String deliveryDestination = "test";
        PowerMockito.mockStatic(Destination.class);
        BDDMockito.given(Destination.instantOfDelivery(truck, deliveryDestination)).willReturn(5);

        truck.start(deliveryDestination);

        PowerMockito.verifyStatic(Destination.class);
        Destination.instantOfDelivery(truck, deliveryDestination);
    }

    @Test
    public void Given_Instant_Of_Delivery_And_Truck1_Started_When_Instant_Of_Return_Not_Reached_Then_Truck1_Not_Available() {
        final int instantOfDelivery = 5;
        String deliveryDestination = "test";
        PowerMockito.mockStatic(Destination.class);
        BDDMockito.given(Destination.instantOfDelivery(truck, deliveryDestination)).willReturn(instantOfDelivery);

        truck.start(deliveryDestination);
        truck.update(Mockito.mock(DeliveryClock.class), 1);


        assertThat(availability.isAvailable(truck)).isFalse();
    }

    @Test
    public void Given_Instant_Of_Arrival_To_Facotry_And_Truck1_Started_When_Instant_Of_Return_Reached_Then_Truck1_Available() {
        PowerMockito.mockStatic(Destination.class);
        BDDMockito.given(Destination.instantOfArrivalToFactory(truck, "test")).willReturn(5);

        truck.start("test");
        truck.update(Mockito.mock(DeliveryClock.class), 5);

        verify(availability).makeUnavailable(truck);
    }

    @Test
    public void Given_Instant_Of_Delivery_And_Truck1_Started_When_Instant_Of_Return_Reached_Then_Truck1_Delivers_The_Cargo() {
        PowerMockito.mockStatic(Destination.class);
        BDDMockito.given(Destination.instantOfDelivery(truck, "test")).willReturn(5);

        truck.start("test");
        truck.update(Mockito.mock(DeliveryClock.class), 5);

        verify(deliveredItems).store("test", 5);
    }

    @Test
    public void When_Truck1_And_Truck1_Compared_Then_They_Are_Equal() {
        assertThat(new Truck(availability, deliveredItems)).isEqualTo(new Truck(availability, deliveredItems));
    }
}