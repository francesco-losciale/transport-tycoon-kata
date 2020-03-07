package com.kata.tt;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class DeliveryClock extends Observable {

    private Integer currentInstant = 0;

    private List<Observer> observerList = new ArrayList<>();

    public void tick() {
        currentInstant++;
        notifyObservers();
    }

    public Integer currentInstant() {
        return currentInstant;
    }

    @Override
    public synchronized void addObserver(Observer observer) {
        this.observerList.add(observer);
    }

    @Override
    public void notifyObservers() {
        this.observerList.stream().forEach(observer -> observer.update(this, currentInstant));
    }
}
