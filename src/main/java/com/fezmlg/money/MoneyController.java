package com.fezmlg.money;

import com.fezmlg.order.Order;
import com.fezmlg.order.OrderController;
import com.fezmlg.staff.Staff;
import com.fezmlg.staff.StaffController;
import com.fezmlg.ui.UIMenu;

public class MoneyController {

    private final OrderController orderController;
    private final StaffController staffController;
    private double sum = 0;

    public MoneyController(OrderController orderController, StaffController staffController) {
        this.orderController = orderController;
        this.staffController = staffController;
    }

    public UIMenu uiMainMenu() {
        UIMenu uiMenu = new UIMenu("Menu", false);

        uiMenu.addText("Orders: " + this.countMoneyFromOrders(this.orderController));
        uiMenu.addText("Tips: " + this.countTips(this.staffController));
        uiMenu.addText("Sum: " + this.sum);

        return uiMenu;
    }

    public double countMoneyFromOrders(OrderController orderController) {
        for (Order order :
                orderController.getOrderList()) {
            sum += order.countMoneyFromItems();
        }
        return sum;
    }

    public double countTips(StaffController staffController) {
        double sum = 0;
        for (Staff staff :
                staffController.getStaffArrayList()) {
            sum += staff.getTips();
        }
        return sum;
    }
}
