package com.fezmlg.menu;

import com.fezmlg.ui.UI;
import com.fezmlg.ui.UIMenu;
import com.fezmlg.ui.UIMenuOption;
import com.fezmlg.utils.JSONSaver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Menu {

    private ArrayList<MenuItem> menuList = new ArrayList<>();

    public Menu(){}

    public void show(){
        UIMenuOption uiMenuOption = new UIMenuOption(1, "Dodaj do menu");
        UIMenuOption uiMenuOption2 = new UIMenuOption(2, "Edytuj pozycję");
        UIMenu uiMenu = new UIMenu(uiMenuOption, uiMenuOption2);
        UI ui = new UI();
        int chosen = uiMenu.showAndChoose();
        switch (chosen){
            case 1:
                this.menuList.add(itemMaker());
                ui.println("added to menu");
        }


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

    public ArrayList<MenuItem> getMenuList() {
        return menuList;
    }

    public void setMenuList(ArrayList<MenuItem> menuList) {
        this.menuList = menuList;
    }

    public void save(){
        new JSONSaver().saveToFile(menuList, "menu");
    }
}
