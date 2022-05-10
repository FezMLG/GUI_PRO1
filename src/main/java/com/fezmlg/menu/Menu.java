package com.fezmlg.menu;

import com.fezmlg.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.function.Predicate;

public class Menu {

    private ArrayList<MenuItem> menuList = new ArrayList<MenuItem>();

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
}
