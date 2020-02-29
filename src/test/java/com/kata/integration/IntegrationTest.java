package com.kata.integration;

import com.kata.tt.Availability;
import com.kata.tt.DeliveredItems;
import com.kata.tt.Delivery;
import com.kata.tt.DeliveryClock;
import com.kata.tt.Truck1;
import org.javatuples.Pair;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class IntegrationTest {

    @Test
    public void When_Truck1_To_B_Then_Instant_Is_5() {
        DeliveryClock deliveryClock = new DeliveryClock();
        Availability availability = new Availability(deliveryClock);
        DeliveredItems deliveredItems = new DeliveredItems();
        Truck1 truck1 = new Truck1(availability);
        availability.register(truck1);
        Delivery delivery = new Delivery(availability, deliveryClock, deliveredItems);

        delivery.process("B");

        assertThat(deliveredItems.items()).contains(new Pair<>("B", 5));
        assertThat(deliveryClock.currentInstant()).isEqualTo(5);
    }
}
