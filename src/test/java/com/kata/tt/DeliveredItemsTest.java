package com.kata.tt;

import javafx.util.Pair;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class DeliveredItemsTest {

    @Test
    public void When_New_Item_Delivered_Then_Check_It_Is_Stored() {
        DeliveredItems deliveredItems = new DeliveredItems();

        deliveredItems.store("B", 5);

        assertThat(deliveredItems.items()).contains(new Pair<>("B", 5));
    }
}