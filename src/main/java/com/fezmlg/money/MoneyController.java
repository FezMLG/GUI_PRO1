package com.fezmlg.money;

import com.fezmlg.order.Order;
import com.fezmlg.order.OrderController;
import com.fezmlg.ui.UIMenu;

public class MoneyController {

    private OrderController orderController;
    private double sum = 0;

    public MoneyController(OrderController orderController) {
        this.orderController = orderController;
    }

    public UIMenu uiMainMenu() {
        UIMenu uiMenu = new UIMenu("Menu", false);

        uiMenu.addText("Orders: " + this.countMoneyFromOrders(this.orderController));
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
}
