package com.fezmlg;

import com.fezmlg.menu.MenuController;
import com.fezmlg.money.MoneyController;
import com.fezmlg.order.OrderController;
import com.fezmlg.worker.WorkerController;
import com.fezmlg.staff.StaffController;
import com.fezmlg.ui.UIMenu;
import com.fezmlg.ui.UIMenuOption;

public class Main {

    private static UIMenu mainMenu;
    private static MenuController menuController;
    private static OrderController orderController;
    private static MoneyController moneyController;
    private static StaffController staffController;

    public static void main(String[] args) {
        menuController = new MenuController();
        orderController = new OrderController();
        staffController = new StaffController();
        moneyController = new MoneyController(orderController, staffController);
        loadDemo();
        orderController.setMenu(menuController);
        start();
        mainMenu = new UIMenu("Main Menu", true);

        mainMenu.addOption(1, new UIMenuOption("Menu", () -> mainMenu.goToMenu(menuController.uiMainMenu()), false));
        mainMenu.addOption(2, new UIMenuOption("Orders", () -> mainMenu.goToMenu(orderController.uiMainMenu()), false));
        mainMenu.addOption(3, new UIMenuOption("Staff", () -> mainMenu.goToMenu(staffController.uiMainMenu()), false));
        mainMenu.addOption(4, new UIMenuOption("Money", () -> mainMenu.goToMenu(moneyController.uiMainMenu()), false));
        mainMenu.addOption(5, new UIMenuOption("Save all", Main::saveAll, false));
        mainMenu.open();
    }

    public static void loadDemo() {
        menuController.load();
        orderController.load();
        staffController.load();
    }

    public static void saveAll() {
        orderController.save();
        staffController.save();
        menuController.save();
    }

    public static void start() {
        WorkerController workerController = new WorkerController(orderController, staffController);
        workerController.start();
    }
}