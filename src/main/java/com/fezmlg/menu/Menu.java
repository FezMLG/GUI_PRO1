package com.fezmlg.menu;

import com.fezmlg.utils.JSONSaver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Menu {

    private ArrayList<MenuItem> menuList = new ArrayList<>();

    public Menu(){}

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
