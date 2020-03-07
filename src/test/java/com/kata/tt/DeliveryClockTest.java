package com.kata.tt;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import sun.font.TrueTypeFont;

import java.util.Observable;
import java.util.Observer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class DeliveryClockTest {

    private DeliveryClock deliveryClock;

    @Before
    public void setUp() throws Exception {
        deliveryClock = new DeliveryClock();
    }

    @Test
    public void When_DeliveryClock_Created_Then_Current_Instant_Is_Zero() {
        assertThat(deliveryClock.currentInstant()).isEqualTo(0);
    }


    @Test
    public void When_Tick_Then_Counter_Of_Unit_Of_Time_Is_Increased() {
        deliveryClock.tick();

        assertThat(deliveryClock.currentInstant()).isEqualTo(1);
    }

    @Test
    public void When_DeliveryClock_Created_Then_Add_Observers_And_Verify() {
        final Observer mockObserver1 = Mockito.mock(Observer.class);
        final Observer mockObserver2 = Mockito.mock(Observer.class);
        deliveryClock.addObserver(mockObserver1);
        deliveryClock.addObserver(mockObserver2);

        deliveryClock.tick();

        verify(mockObserver1).update(any(), any());
        verify(mockObserver2).update(any(), any());
    }


    @Test
    public void Can_Truck1_Observe_DeliveryClock_Ticks() {
        DeliveryClock deliveryClock = new DeliveryClock();
        Truck1 truck1 = Mockito.mock(Truck1.class);
        deliveryClock.addObserver(truck1);

        deliveryClock.tick();
        deliveryClock.tick();
        deliveryClock.tick();

        verify(truck1).update(any(), eq(1));
        verify(truck1).update(any(), eq(2));
        verify(truck1).update(any(), eq(3));
    }
}