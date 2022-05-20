package com.fezmlg.restaurant.worker;

import com.fezmlg.order.Order;
import com.fezmlg.order.OrderController;
import com.fezmlg.order.OrderStatus;
import com.fezmlg.order.OrderType;
import com.fezmlg.staff.Staff;
import com.fezmlg.staff.StaffController;
import com.fezmlg.staff.StaffType;

public class WorkerController extends Thread {

    private OrderController orderController;
    private StaffController staffController;

    public WorkerController(OrderController orderController, StaffController staffController) {
        this.orderController = orderController;
        this.staffController = staffController;
        System.out.println("Starting workerController");
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            //Order dummyOrder = new Order();
            Order orderToMake = null;
            try {
                for (Order order : orderController.getOrderList()) {
                    /*System.out.println(order.getOrderTime().compareTo(orderToMake.getOrderTime()));
                    if (order.getOrderTime().compareTo(orderToMake.getOrderTime()) < 0 && order.getOrderStatus() == OrderStatus.WAITING) {
                        orderToMake = order;
                    }*/
                    if (!order.getOrderStatus().equals(OrderStatus.WAITING)) {
                        continue;
                    }

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
                if (orderToMake != null) {
                    for (Staff staff : staffController.getStaffArrayList()) {
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
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
