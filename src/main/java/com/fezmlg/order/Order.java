package com.fezmlg.order;

import com.fezmlg.menu.MenuItem;
import com.fezmlg.utils.IDGenerator;
import com.fezmlg.utils.RandomNumber;

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
    public OrderStatus orderStatus;

    public Order(Integer id, OrderType orderType, String address, OrderStatus orderStatus) {
        this.id = id;
        this.orderType = orderType;
        this.address = address;
        this.orderTime = LocalDateTime.now();
        this.orderStatus = orderStatus;
    }

    public Order() {

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

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double countMoneyFromItems() {
        double sum = 0;
        for (MenuItem menuItem :
                this.orderItems) {
            sum += menuItem.getPrice();
        }
        return sum;
    }

    public Order generateRandom(OrderController orderController) {
        OrderType randOrderType = OrderType.ONLINE;
        String address = "Palaca 10";
        if (new RandomNumber().get(0, 10) % 2 == 0) {
            randOrderType = OrderType.LOCAL;
            address = "Table 10";
        }
        return new Order(orderController.getOrderList().size() + 1, randOrderType, address, OrderStatus.WAITING);
    }
}

