package com.fezmlg.restaurant.worker;

import com.fezmlg.order.Order;
import com.fezmlg.order.OrderStatus;
import com.fezmlg.staff.Staff;

public class Worker implements Runnable {

    private final Staff staff;
    private final Order order;
    private final OrderStatus orderStatus;
    public Worker(Staff staff, Order order, OrderStatus orderStatus) {
        this.order = order;
        this.staff = staff;
        this.orderStatus = orderStatus;
//        while (thread.isAlive()) {
//            System.out.println("Waiting...");
//        }
        System.out.println("Starting worker");
    }

    public void run() {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        order.setOrderStatus(orderStatus);
        staff.setAvailable(true);
        System.out.println("Meal is prepared");
    }
    
}
