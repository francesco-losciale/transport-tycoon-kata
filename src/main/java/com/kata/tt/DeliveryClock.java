package com.kata.tt;

public class DeliveryClock {

    private Integer currentInstant = 0;

    public void tick() {
        currentInstant++;
    }

    public Integer currentInstant() {
        return currentInstant;
    }
}
