package com.fezmlg;

import com.fezmlg.menu.Menu;
import com.fezmlg.menu.MenuItem;
import com.fezmlg.order.Order;
import com.fezmlg.order.OrderController;
import com.fezmlg.order.OrderType;
import com.fezmlg.ui.UIMenu;
import com.fezmlg.ui.UIMenuOption;

public class Main {

    private static UIMenu mainMenu;
    private static Menu menu;
    private static OrderController orderController;

    public static void main(String[] args) {
        menu = new Menu();
        orderController = new OrderController();
        loadDemo();
        orderController.setMenu(menu);
        mainMenu = new UIMenu("Main Menu", true);

        mainMenu.addOption(1, new UIMenuOption("Menu", () -> {
            mainMenu.goToMenu(menu.getMenuMenu());
        }, false));
        mainMenu.addOption(2, new UIMenuOption("Orders", () -> {
            mainMenu.goToMenu(orderController.getOrderMenu());
        }, false));

        mainMenu.open();
    }

    public static void loadDemo(){
        menu.addToMenu(new MenuItem("Item 1", "desc for item 1", 1 ,true ));
        menu.addToMenu(new MenuItem("Item 2", "desc for item 2", 2 ,true ));
        menu.addToMenu(new MenuItem("Item 3", "desc for item 3", 3 ,true ));
        Order order = new Order(OrderType.LOCAL, "address 1");
        for (MenuItem item :
                menu.getMenuList()) {
            order.addToOrder(item);
        }
        orderController.addOrder(order);
    }
}