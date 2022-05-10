package com.fezmlg.order;

import com.fezmlg.utils.JSONSaver;

import java.util.ArrayList;

public class OrderList {

    private ArrayList<Order> orderList = new ArrayList<>();

    public OrderList() {
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void addOrder(Order order) {
        this.orderList.add(order);
    }

    public void save(){
        new JSONSaver().saveToFile(orderList, "orders");
    }

}
