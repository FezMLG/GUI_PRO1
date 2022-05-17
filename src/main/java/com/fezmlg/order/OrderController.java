package com.fezmlg.order;

import com.fezmlg.menu.Menu;
import com.fezmlg.menu.MenuItem;
import com.fezmlg.ui.UI;
import com.fezmlg.ui.UIMenu;
import com.fezmlg.ui.UIMenuOption;
import com.fezmlg.utils.JSONSaver;

import java.util.ArrayList;

public class OrderController {
    private static UI ui = new UI();
    private ArrayList<Order> orderList = new ArrayList<>();
    private Menu menu;

    public OrderController() {
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public UIMenu getOrderMenu() {
        UIMenu uiMenu = new UIMenu("Menu", false);

        uiMenu.addOption(1, new UIMenuOption("Show orders and manage items", () -> {
            uiMenu.goToMenu(this.getOrderItems());
        }, false));
        uiMenu.addOption(2, new UIMenuOption("Initialize new order", this::orderMaker, false));

        return uiMenu;
    }

    public UIMenu getOrderItems() {
        UIMenu uiMenu = new UIMenu("Order List", false);

        int i = 1;
        for (Order item : orderList) {
            uiMenu.addOption(i, new UIMenuOption(item.getOrderType() + " " + item.getAddress(), () -> {
                uiMenu.goToMenu(this.getOrderItem(item));
            }, false));
//            System.out.println(item.getName() + " " + item.getDescription());
            i++;
        }

        return uiMenu;
    }

    public UIMenu getOrderItem(Order order) {
        UIMenu uiMenu = new UIMenu(order.getOrderType() + " " + order.getAddress(), false);
        int i = 1;
        for (MenuItem item : order.orderItems) {
            uiMenu.addText(
                "Name: " + item.getName(),
                "Description: " + item.getDescription(),
                "Price: " + item.getPrice()
            );
//            System.out.println(order.getName() + " " + order.getDescription());
            i++;
        }
        uiMenu.addOption(i + 1, new UIMenuOption("Add item to order", () -> {
            uiMenu.goToMenu(this.modifyItemList(true, order));
        }, false));
        uiMenu.addOption(i + 2, new UIMenuOption("Remove item from order", () -> {
            uiMenu.goToMenu(this.modifyItemList(false, order));
        }, false));         //TODO add and remove item from order
        return uiMenu;
    }

    private UIMenu modifyItemList(boolean save, Order order) {
        UIMenu uiMenu = new UIMenu("Choose order to " + save, false);
        int i = 1;
        if (save) {
            for (MenuItem item :
                    menu.getMenuList()) {
                uiMenu.addOption(i, new UIMenuOption(uiMenu.multiLineBuilder(
                        "Name: " + item.getName(),
                        "Description: " + item.getDescription(),
                        "Price: " + item.getPrice()
                ), () -> order.addToOrder(item), false));
                i++;
            }
        } else {
            for (MenuItem item :
                    menu.getMenuList()) {
                uiMenu.addOption(i, new UIMenuOption(uiMenu.multiLineBuilder(
                        "Name: " + item.getName(),
                        "Description: " + item.getDescription(),
                        "Price: " + item.getPrice()
                ), () -> order.removeFromOrder(item), false));
                i++;
            }
        }
        return uiMenu;
    }

    private void itemPrinter(MenuItem item) {
        ui.println("ID: " + String.valueOf(item.getId()));
        ui.println("Name: " + item.getName());
        ui.println("Description: " + item.getDescription());
        ui.println("Price: " + String.valueOf(item.getPrice()));
        ui.listenForInput();
    }

    private void orderMaker() {
        ui.println("Select order type:");
        ui.println("1 - Local, 2 - Remote");
        boolean orderBoolean = ui.listenForAcceptance("1", "2");
        OrderType orderType;
        String address;
        if (orderBoolean) {
            orderType = OrderType.LOCAL;
            ui.println("Table number");
            address = ("Table " + ui.listenForInput());
        } else {
            orderType = OrderType.ONLINE;
            ui.println("Address");
            address = ui.listenForInput();
        }
        orderList.add(new Order(orderType, address));
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void addOrder(Order order) {
        this.orderList.add(order);
    }

    public void save() {
        new JSONSaver().saveToFile(orderList, "orders");
    }
}
