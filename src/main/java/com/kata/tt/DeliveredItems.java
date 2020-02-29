package com.kata.tt;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeliveredItems {

    private final ArrayList<Pair<String, Integer>> deliveries = new ArrayList<>();

    public List<Pair<String, Integer>> items() {
        return Collections.unmodifiableList(deliveries);
    }

    public void store(String deliveryDestination, Integer deliveryInstant) {
        deliveries.add(new Pair<>(deliveryDestination, deliveryInstant));
    }
}
