package com.fezmlg.utils;

import com.fezmlg.menu.MenuController;
import com.fezmlg.money.MoneyController;
import com.fezmlg.order.OrderController;
import com.fezmlg.staff.StaffController;

public class SaverWorker extends Thread {

    private static OrderController orderController;
    private static StaffController staffController;
    private static MenuController menuController;

    public SaverWorker(OrderController orderController, StaffController staffController, MenuController menuController) {
        SaverWorker.orderController = orderController;
        SaverWorker.staffController = staffController;
        SaverWorker.menuController = menuController;
    }

    public void run() {
        orderController.save();
        staffController.save();
        menuController.save();
    }
}
