package com.fezmlg.order;

import com.fezmlg.menu.MenuItem;
import com.fezmlg.utils.IDGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;


public class Order {

    public int id;
    public OrderType orderType;
    public String address;
    public LocalDateTime orderTime;
    public ArrayList<MenuItem> orderItems = new ArrayList<>();

    public Order(Integer id, OrderType orderType, String address) {
        this.id = id;
        this.orderType = orderType;
        this.address = address;
        this.orderTime = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public ArrayList<MenuItem> getOrderItems() {
        return orderItems;
    }

    public void addToOrder(MenuItem... itemToAdd) {
        this.orderItems.addAll(Arrays.asList(itemToAdd));
    }

    public void addToOrder(MenuItem itemToAdd) {
        this.orderItems.add(itemToAdd);
        System.out.println("Added " + itemToAdd.getName() + " to order.");
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

    public void setId(int id) {
        this.id = id;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public void setOrderItems(ArrayList<MenuItem> orderItems) {
        this.orderItems = orderItems;
    }
}
