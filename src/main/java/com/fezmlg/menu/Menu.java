package com.fezmlg.menu;

import java.util.ArrayList;

public class Menu {

    private ArrayList<MenuItem> menuList = new ArrayList<MenuItem>();

    public Menu(){}

    public Menu addToMenu(MenuItem itemToAdd){
        this.menuList.add(itemToAdd);
        return this;
    }

    public ArrayList<MenuItem> getMenuList() {
        return menuList;
    }

    public void setMenuList(ArrayList<MenuItem> menuList) {
        this.menuList = menuList;
    }
}
