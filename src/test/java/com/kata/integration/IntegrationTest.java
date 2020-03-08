package com.kata.integration;

import com.kata.tt.Availability;
import com.kata.tt.DeliveredItems;
import com.kata.tt.Delivery;
import com.kata.tt.DeliveryClock;
import com.kata.tt.Truck;
import org.javatuples.Pair;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationTest {

    private DeliveryClock deliveryClock = new DeliveryClock();
    private Availability availability = new Availability();
    private DeliveredItems deliveredItems = new DeliveredItems();
    private Truck truck = new Truck(availability, deliveredItems);
    private Delivery delivery = new Delivery(availability, deliveryClock, truck);

    @Before
    public void setUp() throws Exception {
        availability.makeAvailable(truck);
    }

    @Test
    public void When_Truck1_Arrives_To_B_Then_Instant_Is_5() {
        delivery.process("B");

        assertThat(deliveredItems.items()).contains(new Pair<>("B", 5));
    }


    @Test
    public void When_Truck1_Returns_To_Factory_Then_Instant_Is_10() {
        delivery.process("B");

        assertThat(deliveryClock.currentInstant()).isEqualTo(10);
    }
}
