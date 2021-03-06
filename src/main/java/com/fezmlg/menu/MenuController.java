package com.fezmlg.menu;

import com.fezmlg.ui.UI;
import com.fezmlg.ui.UIMenu;
import com.fezmlg.ui.UIMenuOption;
import com.fezmlg.utils.JSONSaver;

import java.util.ArrayList;
import java.util.Arrays;

public class MenuController {

    private ArrayList<MenuItem> menuList = new ArrayList<>();
    private static UI ui;

    public MenuController() {
        ui = new UI();
    }

    public UIMenu uiMainMenu() {
        UIMenu uiMenu = new UIMenu("Menu", true);
        uiMenu.addOption(1, new UIMenuOption("Show and Manage Items in Menu", () -> uiMenu.goToMenu(this.getMenuItems()), false));
        uiMenu.addOption(2, new UIMenuOption("Add item to menu", this::itemMaker, false));
        return uiMenu;
    }

    public UIMenu getMenuItems() {
        UIMenu uiMenu = new UIMenu("Menu List", false);

        int i = 1;
        for (MenuItem menuItem : menuList) {
            uiMenu.addOption(i, new UIMenuOption(menuItem.getName(), () -> uiMenu.goToMenu(this.getMenuItem(menuItem)), true));
            i++;
        }

        return uiMenu;
    }

    public UIMenu getMenuItem(MenuItem item) {
        UIMenu uiMenu = new UIMenu(item.getName(), false);
        uiMenu.addOption(1, new UIMenuOption("ID: " + item.getId(), () -> {
        }, false));
        uiMenu.addOption(2, new UIMenuOption("Name: " + item.getName(), () -> {
        }, false));
        uiMenu.addOption(3, new UIMenuOption("Description: " + item.getDescription(), () -> {
        }, false));
        uiMenu.addOption(4, new UIMenuOption("Is Available: " + item.isAvailable(), () -> {
        }, false));
        uiMenu.addOption(5, new UIMenuOption("Set availability", () -> this.setAvailability(item), false));
        uiMenu.addOption(6, new UIMenuOption("Remove", () -> this.removeItem(item), false));

        return uiMenu;
    }

    public void setAvailability(MenuItem item) {
        ui.println("Is product available? Y/N");
        boolean isAvailable = ui.listenForAcceptance("Y");
        item.setAvailable(isAvailable);
    }

    private void itemMaker() {
        ui.println("Product name:");
        String name = ui.listenForInput();
        ui.println("Product description:");
        String desc = ui.listenForInput();
        ui.println("Product price:");
        double price = Double.parseDouble(ui.listenForInput());

        ui.println("Is product available? Y/N");
        boolean isAvailable = ui.listenForAcceptance("Y");

        ui.println(name, desc, String.valueOf(price), String.valueOf(isAvailable));
        this.addToMenu(new MenuItem(menuList.size() + 1, name, desc, price, isAvailable));
    }

    public MenuController addToMenu(MenuItem... itemToAdd) {
        this.menuList.addAll(Arrays.asList(itemToAdd));
        return this;
    }

    public void removeItem(MenuItem... itemToRemove) {
        for (int i = 0; i < itemToRemove.length; i++) {
            int finalI = i;
            this.menuList.removeIf(n -> n.getId() == itemToRemove[finalI].getId());
        }
    }

    public ArrayList<MenuItem> getMenuList() {
        return menuList;
    }

    public void setMenuList(ArrayList<MenuItem> menuList) {
        if (menuList == null) return;
        this.menuList = menuList;
    }

    public void save() {
        new JSONSaver().saveToFile(this.menuList, "menu");
    }

    public void load() {
        ArrayList<MenuItem> data = new JSONSaver().loadFromFileMenu("menu");
        this.setMenuList(data);
    }
}
