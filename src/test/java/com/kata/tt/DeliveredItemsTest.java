package com.kata.tt;

import javafx.util.Pair;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class DeliveredItemsTest {

    @Test
    public void When_New_Item_Delivered_Then_Check_It_Is_Stored() {
        Pair<String, Integer> element = new Pair<>("B", 5);
        DeliveredItems deliveredItems = new DeliveredItems();

        deliveredItems.store(element);

        assertThat(deliveredItems.items()).contains(element);
    }
}