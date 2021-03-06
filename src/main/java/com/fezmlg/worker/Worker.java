package com.fezmlg.worker;

import com.fezmlg.order.Order;
import com.fezmlg.order.OrderStatus;
import com.fezmlg.staff.Staff;
import com.fezmlg.staff.StaffType;
import com.fezmlg.utils.RandomNumber;

public class Worker implements Runnable {

    private final Staff staff;
    private final Order order;
    private final OrderStatus orderStatus;
    private final Integer timeToSleep;

    public Worker(Staff staff, Order order, OrderStatus orderStatus, Integer timeToSleep) {
        this.order = order;
        this.staff = staff;
        this.orderStatus = orderStatus;
        this.timeToSleep = timeToSleep;
    }

    public void run() {
        try {
            Thread.sleep(timeToSleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (staff.getStaffType() != StaffType.COOK) {
            RandomNumber randomNumber = new RandomNumber();
            double tipPercent = (double) randomNumber.get(0, 10) / 100;
            staff.addTips(order.countMoneyFromItems() * tipPercent);
        }
        order.setOrderStatus(orderStatus);
        staff.setAvailable(true);
    }

}
