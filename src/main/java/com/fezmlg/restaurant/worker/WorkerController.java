package com.fezmlg.restaurant.worker;

import com.fezmlg.order.Order;
import com.fezmlg.order.OrderController;
import com.fezmlg.order.OrderStatus;
import com.fezmlg.order.OrderType;
import com.fezmlg.staff.Staff;
import com.fezmlg.staff.StaffController;
import com.fezmlg.staff.StaffType;

public class WorkerController extends Thread {

    private final OrderController orderController;
    private final StaffController staffController;

    public WorkerController(OrderController orderController, StaffController staffController) {
        this.orderController = orderController;
        this.staffController = staffController;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            //Order dummyOrder = new Order();
            Order orderToMake = null;
            Order orderToDeliver = null;
            try {
                for (Order order : orderController.getOrderList()) {
                    if (order.getOrderStatus().equals(OrderStatus.WAITING)) {
                        if (orderToMake == null) {
                            orderToMake = order;
                        } else {
                            if (orderToMake.getOrderType().equals(OrderType.ONLINE)) {
                                orderToMake = order;
                            } else {
                                if (order.getOrderTime().compareTo(orderToMake.getOrderTime()) < 0) {
                                    orderToMake = order;
                                }
                            }
                        }
                    }

                    if (order.getOrderStatus().equals(OrderStatus.READY)) {
                        if (orderToDeliver == null) {
                            orderToDeliver = order;
                        } else {
                            if (orderToDeliver.getOrderType().equals(OrderType.ONLINE)) {
                                orderToDeliver = order;
                            } else if (orderToDeliver.getOrderType().equals(OrderType.LOCAL)) {
                                orderToDeliver = order;
                            }
                        }
                    }
                }
                if (orderToMake != null) {
                    for (Staff staff : staffController.getStaffArrayList()) {
                        if (staff.getStaffType() == StaffType.COOK && staff.isAvailable()) {
                            staff.setAvailable(false);
                            orderToMake.setOrderStatus(OrderStatus.IN_PROGRESS);
                            Worker worker = new Worker(staff, orderToMake, OrderStatus.READY, 5000);
                            Thread thread = new Thread(worker);
                            thread.start();
                            break;
                        }
                    }
                }

                if (orderToDeliver != null) {
                    for (Staff staff : staffController.getStaffArrayList()) {
                        if (staff.getStaffType() == StaffType.DELIVERY && staff.isAvailable()) {
                            staff.setAvailable(false);
                            orderToDeliver.setOrderStatus(OrderStatus.IN_DELIVERY);
                            Worker worker = new Worker(staff, orderToDeliver, OrderStatus.COMPLETED, 500);
                            Thread thread = new Thread(worker);
                            thread.start();
                            break;
                        } else if (staff.getStaffType() == StaffType.WAITER && staff.isAvailable()) {
                            staff.setAvailable(false);
                            orderToDeliver.setOrderStatus(OrderStatus.IN_DELIVERY);
                            Worker worker = new Worker(staff, orderToDeliver, OrderStatus.COMPLETED, 100);
                            Thread thread = new Thread(worker);
                            thread.start();
                            break;
                        }
                    }
                }
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
