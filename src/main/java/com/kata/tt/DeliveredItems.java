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

    public void store(Pair<String, Integer> newCompletedDelivery) {
        deliveries.add(newCompletedDelivery);
    }
}
