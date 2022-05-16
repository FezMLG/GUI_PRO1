package com.fezmlg.menu;

import com.fezmlg.ui.UI;
import com.fezmlg.ui.UIMenu;
import com.fezmlg.ui.UIMenuOption;
import com.fezmlg.utils.JSONSaver;

import java.util.ArrayList;
import java.util.Arrays;

public class Menu {

    private ArrayList<MenuItem> menuList = new ArrayList<>();

    public Menu(){}

    public UIMenu getMenuMenu(){
        UIMenu uiMenu = new UIMenu("Menu", true);

        uiMenu.addOption(1, new UIMenuOption("Show Items in Menu", () -> {
            uiMenu.goToMenu(this.getMenuList());
        }, false));

        return uiMenu;
    }

//    public UIMenu getMenuList(){
//        UIMenu uiMenu = new UIMenu("Menu List", false);
//
//        int i = 1;
//        for(MenuItem menuItem : menuList){
//            uiMenu.addOption(i, new UIMenuOption(menuItem.getName(), () -> {
//                uiMenu.goToMenu(menu.getShipMenu());
//            }, true));
////            System.out.println(menuItem.getName() + " " + menuItem.getDescription());
//            i++;
//        }
//
//        return uiMenu;
//    }

    public UIMenu getMenuList(){
        UIMenu uiMenu = new UIMenu("Menu List", false);

        int i = 1;
        for(MenuItem menuItem : menuList){
            uiMenu.addOption(i, new UIMenuOption(menuItem.getName(), () -> System.out.println(menuItem.getName() + " " + menuItem.getDescription()), true));
            i++;
        }

        return uiMenu;
    }

    private MenuItem itemMaker(){
        UI ui = new UI();
        ui.println("Product name:");
        String name = ui.listenForInput();
        ui.println("Product description:");
        String desc = ui.listenForInput();
        ui.println("Product price:");
        double price = Double.parseDouble(ui.listenForInput());

        ui.println("Is product available? Y/N");
        boolean isAvailable = ui.listenForAcceptance("Y", "N");

        ui.println(name, desc, String.valueOf(price), String.valueOf(isAvailable));
        return new MenuItem(name, desc, price, isAvailable);
    }

    public Menu addToMenu(MenuItem... itemToAdd) {
        this.menuList.addAll(Arrays.asList(itemToAdd));
        return this;
    }

    public Menu removeItem(MenuItem... itemToRemove) {
        for (int i = 0; i < itemToRemove.length; i++) {
            int finalI = i;
            this.menuList.removeIf(n -> n.getId() == itemToRemove[finalI].getId());
        }
        return this;
    }

//    public ArrayList<MenuItem> getMenuList() {
//        return menuList;
//    }

    public void setMenuList(ArrayList<MenuItem> menuList) {
        this.menuList = menuList;
    }

    public void save(){
        new JSONSaver().saveToFile(menuList, "menu");
    }
}
