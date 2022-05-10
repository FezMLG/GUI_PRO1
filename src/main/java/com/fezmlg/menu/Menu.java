package com.fezmlg.menu;

import com.fezmlg.utils.JSONSaver;

import java.io.IOException;
import java.util.ArrayList;

public class Menu {

    private ArrayList<MenuItem> menuList = new ArrayList<>();

    public Menu(){}

    public Menu addToMenu(MenuItem itemToAdd){
        this.menuList.add(itemToAdd);
        return this;
    }

    public Menu removeItem(MenuItem itemToRemove) {
        this.menuList.removeIf(n -> n.getId() == itemToRemove.getId());
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
