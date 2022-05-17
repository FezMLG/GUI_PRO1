package com.fezmlg.order;

import com.fezmlg.menu.MenuItem;
import com.fezmlg.ui.UI;
import com.fezmlg.utils.IDGenerator;
import com.fezmlg.utils.JSONSaver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;


public class Order {

    public UUID id;
    public OrderType orderType;
    public String address;
    public ArrayList<MenuItem> orderItems = new ArrayList<>();

    public Order(OrderType orderType, String address) {
        this.id = new IDGenerator().getID();
        this.orderType = orderType;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public ArrayList<MenuItem> getOrderItems() {
        return orderItems;
    }

    public Order addToOrder(MenuItem... itemToAdd) {
        this.orderItems.addAll(Arrays.asList(itemToAdd));
        return this;
    }

    public void removeFromOrder(MenuItem... itemToRemove) {
        for (int i = 0; i < itemToRemove.length; i++) {
            int finalI = i;
            this.orderItems.removeIf(n -> n.getId() == itemToRemove[finalI].getId());
        }
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public String getAddress() {
        return address;
    }
}
