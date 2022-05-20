package com.fezmlg.order;

import com.fezmlg.menu.Menu;
import com.fezmlg.menu.MenuItem;
import com.fezmlg.ui.UI;
import com.fezmlg.ui.UIMenu;
import com.fezmlg.ui.UIMenuOption;
import com.fezmlg.utils.JSONSaver;
import org.apache.commons.lang3.time.DateUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.ArrayList;

public class OrderController {
    private final UI ui;
    private ArrayList<Order> orderList;
    private Menu menu;

    public OrderController() {
        ui = new UI();
        orderList = new ArrayList<>();
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public UIMenu uiMainMenu() {
        UIMenu uiMenu = new UIMenu("Menu", false);

        uiMenu.addOption(1, new UIMenuOption("Show orders and manage items", () -> uiMenu.goToMenu(this.getOrderItems()), false));
        uiMenu.addOption(2, new UIMenuOption("Initialize new order", this::orderMaker, false));
//        uiMenu.addOption(3, new UIMenuOption("Save orders to file", this::save, false));
//        uiMenu.addOption(4, new UIMenuOption("Load orders from file", this::load, false));
        uiMenu.addOption(5, new UIMenuOption("Add random order", this::addRandomOrder, false));
        uiMenu.addOption(6, new UIMenuOption("Show waiting orders", () -> uiMenu.goToMenu(this.getOrderItems(OrderStatus.WAITING)), false));

        return uiMenu;
    }

    public UIMenu getOrderItems() {
        UIMenu uiMenu = new UIMenu("Order List", false);
        int i = 1;
        for (Order item : orderList) {
            uiMenu.addOption(i, new UIMenuOption(item.getId() + " " + item.getOrderType() + " " + item.getAddress() + " " + item.getOrderStatus(), () -> uiMenu.goToMenu(this.getOrderItem(item)), false));
            i++;
        }

        return uiMenu;
    }

    public UIMenu getOrderItems(OrderStatus status) {
        UIMenu uiMenu = new UIMenu("Order List", false);
        ArrayList<Order> orderList = this.filterByStatus(status);
        int i = 1;
        for (Order item : orderList) {
            uiMenu.addOption(i, new UIMenuOption(item.getId() + " " + item.getOrderType() + " " + item.getAddress() + " " + item.getOrderStatus(), () -> uiMenu.goToMenu(this.getOrderItem(item)), false));
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
        uiMenu.addOption(i + 1, new UIMenuOption("Add item to order", () -> uiMenu.goToMenu(this.modifyItemList(true, order)), true));
        uiMenu.addOption(i + 2, new UIMenuOption("Remove item from order", () -> uiMenu.goToMenu(this.modifyItemList(false, order)), true));
        uiMenu.addOption(i + 2, new UIMenuOption("Set order to waiting", () -> order.setOrderStatus(OrderStatus.WAITING), true));
        return uiMenu;
    }

    private UIMenu modifyItemList(boolean save, Order order) {
        UIMenu uiMenu = new UIMenu("Choose order to " + save, false);
        int i = 1;
        if (save) {
            for (MenuItem item :
                    menu.getMenuList()) {
                if (!item.isAvailable()) {
                    continue;
                }
                uiMenu.addOption(i, new UIMenuOption(uiMenu.multiLineBuilder(
                        "Name: " + item.getName(),
                        "Description: " + item.getDescription(),
                        "Price: " + item.getPrice()
                ), () -> order.addToOrder(item), false));
                i++;
            }
        } else {
            for (MenuItem item :
                    order.getOrderItems()) {
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
        int size = generateID();
        orderList.add(new Order(size, orderType, address, OrderStatus.PLACED));
    }

    public int generateID() {
        int size = 1;
        for (Order order :
                this.orderList) {
            if (DateUtils.isSameDay(convertToDate(order.getOrderTime()), convertToDate(LocalDateTime.now()))) {
                size++;
            }
        }
        return size;
    }

    public Date convertToDate(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        if (orderList == null) return;
        this.orderList = orderList;
    }

    public void addOrder(Order order) {
        this.orderList.add(order);
    }

    public void save() {
        new JSONSaver().saveToFile(orderList, "orderController");
    }

    public void load() {
        ArrayList<Order> data = new JSONSaver().loadFromFileOrder("orderController");
        this.setOrderList(data);
    }

    public void addRandomOrder() {
        this.addOrder(new Order().generateRandom(this));
    }

    public ArrayList<Order> filterByStatus(OrderStatus status) {
        ArrayList<Order> copyList = this.orderList;
        copyList.removeIf(n -> n.orderStatus != status);
        return copyList;
    }
}
