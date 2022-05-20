package com.fezmlg.restaurant.worker;

import com.fezmlg.order.Order;
import com.fezmlg.order.OrderController;
import com.fezmlg.order.OrderStatus;
import com.fezmlg.staff.Staff;
import com.fezmlg.staff.StaffController;
import com.fezmlg.staff.StaffType;
import com.fezmlg.staff.cook.Cook;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class WorkerController implements Runnable {

    private OrderController orderController;
    private StaffController staffController;

    public WorkerController(OrderController orderController, StaffController staffController) {
        this.orderController = orderController;
        this.staffController = staffController;
        System.out.println("Starting workerController");
    }

    public void run() {
        Order dummyOrder = new Order();
        Order orderToMake = new Order();
        try {
            for (Order order :
                    orderController.getOrderList()) {
                System.out.println(order.getOrderTime());
                if (order.getOrderTime().compareTo(orderToMake.getOrderTime()) < 0 && order.getOrderStatus() == OrderStatus.WAITING) {
                    orderToMake = order;
                }
            }
            System.out.println("Taking order to make");
            if (!orderToMake.equals(dummyOrder)) {
                for (Staff staff :
                        staffController.getStaffArrayList()) {
                    if (staff.getStaffType() == StaffType.COOK && staff.isAvailable()) {
                        staff.setAvailable(false);
                        orderToMake.setOrderStatus(OrderStatus.IN_PROGRESS);
                        Worker worker = new Worker(staff, orderToMake, OrderStatus.READY);
                        Thread thread = new Thread(worker);
                        thread.start();
                        break;
                    }
                }
            }
        } catch (NullPointerException ignored) {

        }
    }

}
