package com.fezmlg.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class MenuTest {

    @Test
    @DisplayName("should add item to menu and return array with items")
    void addToMenu() {
        Menu menu = new Menu();
        ArrayList<MenuItem> list = new ArrayList<>();
        MenuItem item = new MenuItem(1, "test", "desc", 4.10, true);
        list.add(item);
        Assertions.assertEquals(menu.addToMenu(item).getMenuItems(), list);
    }

    @Test
    void removeItem() {
        Menu menu = new Menu();
        ArrayList<MenuItem> list = new ArrayList<>();
        MenuItem item = new MenuItem(1, "test", "desc", 4.10, true);
        MenuItem item2 = new MenuItem(2, "test", "desc", 4.10, true);
        menu.addToMenu(item, item2);
        menu.removeItem(item);
        list.add(item2);
        Assertions.assertEquals(menu.getMenuItems(), list);
    }

    @Test
    void canChangeAvailability() {
        MenuItem item = new MenuItem(1, "test", "desc", 4.10, true);
        Assertions.assertTrue(item.isAvailable());
        item.setAvailable(false);
        Assertions.assertFalse(item.isAvailable());
    }

    @Test
    void creatingFileTest() {
        Menu menu = new Menu();
        MenuItem item = new MenuItem(1, "test", "desc", 4.10, true);
        menu.addToMenu(item);
        menu.save();
    }
}