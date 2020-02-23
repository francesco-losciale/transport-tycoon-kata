package com.kata.tt;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class DeliveryClockTest {

    private DeliveryClock deliveryClock = new DeliveryClock();

    @Test
    public void When_DeliveryClock_Created_Then_Current_Instant_Is_Zero() {
        assertThat(deliveryClock.currentInstant()).isEqualTo(0);
    }


    @Test
    public void When_Tick_Then_Counter_Of_Unit_Of_Time_Is_Increased() {
        deliveryClock.tick();

        assertThat(deliveryClock.currentInstant()).isEqualTo(1);
    }
}