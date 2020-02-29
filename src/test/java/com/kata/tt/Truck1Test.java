package com.kata.tt;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Destination.class)
public class Truck1Test {

    @InjectMocks
    private Truck1 truck1;

    @Mock
    private Availability availability;

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(Destination.class);
    }

    @Test
    public void When_Truck1_Starts_Then_Ask_Destination_To_Know_How_Long_It_Would_Take() {
        String deliveryDestination = "test";
        truck1.start(deliveryDestination);

        PowerMockito.verifyStatic(Destination.class);
        Destination.instantOfDelivery(truck1, deliveryDestination);
        Destination.instantOfArrivalToFactory(truck1, deliveryDestination);
    }

    @Test
    public void When_Truck1_Starts_Then_Unavailable_For_Other_Deliveries() {
        String deliveryDestination = "test";
        PowerMockito.mockStatic(Destination.class);
        BDDMockito.given(Destination.instantOfArrivalToFactory(truck1, deliveryDestination)).willReturn(0);

        truck1.start(deliveryDestination);

        verify(availability).unavailable(truck1, 0);
    }

    @Test
    public void When_Truck1_Starts_Then_Availability_Receives_Time_Truck1_Will_Be_Available_Again() {
        String deliveryDestination = "test";
        PowerMockito.mockStatic(Destination.class);
        BDDMockito.given(Destination.instantOfArrivalToFactory(truck1, deliveryDestination)).willReturn(5);

        truck1.start(deliveryDestination);

        verify(availability).unavailable(truck1, 5);
    }

    @Test
    public void When_Truck1_And_Truck1_Compared_Then_They_Are_Equal() {
        assertThat(new Truck1(availability)).isEqualTo(new Truck1(availability));
    }
}