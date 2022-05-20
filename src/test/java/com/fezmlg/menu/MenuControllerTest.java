package com.fezmlg.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class MenuControllerTest {

    @Test
    @DisplayName("should add item to menu and return array with items")
    void addToMenu() {
        MenuController menuController = new MenuController();
        ArrayList<MenuItem> list = new ArrayList<>();
        MenuItem item = new MenuItem(1, "test", "desc", 4.10, true);
        list.add(item);
        Assertions.assertEquals(menuController.addToMenu(item).getMenuItems(), list);
    }

    @Test
    void removeItem() {
        MenuController menuController = new MenuController();
        ArrayList<MenuItem> list = new ArrayList<>();
        MenuItem item = new MenuItem(1, "test", "desc", 4.10, true);
        MenuItem item2 = new MenuItem(2, "test", "desc", 4.10, true);
        menuController.addToMenu(item, item2);
        menuController.removeItem(item);
        list.add(item2);
        Assertions.assertEquals(menuController.getMenuItems(), list);
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
        MenuController menuController = new MenuController();
        MenuItem item = new MenuItem(1, "test", "desc", 4.10, true);
        menuController.addToMenu(item);
        menuController.save();
    }
}