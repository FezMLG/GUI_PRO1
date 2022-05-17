package com.fezmlg.order;

import com.fezmlg.menu.MenuItem;
import com.fezmlg.ui.UI;
import com.fezmlg.ui.UIMenu;
import com.fezmlg.ui.UIMenuOption;
import com.fezmlg.utils.JSONSaver;

import java.util.ArrayList;

public class OrderController {
    private static UI ui = new UI();
    private ArrayList<Order> orderList = new ArrayList<>();

    public OrderController() {

    }

    public UIMenu getOrderMenu() {
        UIMenu uiMenu = new UIMenu("Menu", false);

        uiMenu.addOption(1, new UIMenuOption("Show and Manage Items in Orders", () -> {
            uiMenu.goToMenu(this.getOrderItems());
        }, false));
//        uiMenu.addOption(2, new UIMenuOption("Add item to order", () -> this.itemMaker(), false));

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
            uiMenu.addOption(i, new UIMenuOption(uiMenu.multiLineBuilder(
                    "ID: " + item.getId(),
                    "Name: " + item.getName(),
                    "Description: " + item.getDescription(),
                    "Price: " + item.getPrice()
            ), () -> {
//                this.itemPrinter(item);
            }, false));
//            System.out.println(order.getName() + " " + order.getDescription());
            i++;
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

//    private void itemMaker(){
//        ui.println("Product name:");
//        String name = ui.listenForInput();
//        ui.println("Product description:");
//        String desc = ui.listenForInput();
//        ui.println("Product price:");
//        double price = Double.parseDouble(ui.listenForInput());
//
//        ui.println("Is product available? Y/N");
//        boolean isAvailable = ui.listenForAcceptance("Y", "N");
//
//        ui.println(name, desc, String.valueOf(price), String.valueOf(isAvailable));
//        this.addOrder(new MenuItem(name, desc, price, isAvailable));
//    }

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
