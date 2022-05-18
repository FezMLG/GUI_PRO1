package com.fezmlg.order;

import com.fezmlg.menu.MenuItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void addToOrder() {
        Order order = new Order(1, OrderType.LOCAL, "table 1", OrderStatus.PLACED);
        MenuItem item = new MenuItem("test", "desc", 4.10, true);
        ArrayList<MenuItem> list = new ArrayList<>();
        order.addToOrder(item);
        list.add(item);
        Assertions.assertEquals(order.getOrderItems(), list);
    }

    @Test
    void removeFromOrder() {
    }
}