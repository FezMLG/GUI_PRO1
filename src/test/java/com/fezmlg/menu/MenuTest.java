package com.fezmlg.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    @Test
    @DisplayName("should add item to menu and return array with items")
    void addToMenu() {
        Menu menu = new Menu();
        ArrayList<MenuItem> list = new ArrayList<MenuItem>();
        MenuItem item = new MenuItem(0, "test", "desc", 4.10 );
        list.add(item);
        Assertions.assertEquals(menu.addToMenu(item).getMenuList(), list);
    }

    @Test
    void removeItem() {
        Menu menu = new Menu();
        ArrayList<MenuItem> list = new ArrayList<MenuItem>();
        MenuItem item = new MenuItem(0, "test", "desc", 4.10 );
        menu.addToMenu(item);
        menu.removeItem(item);
        Assertions.assertEquals(menu.getMenuList(), list);

    }
}