package com.fezmlg;

import com.fezmlg.menu.Menu;
import com.fezmlg.ui.UI;
import com.fezmlg.ui.UIMenu;
import com.fezmlg.ui.UIMenuOption;

public class Main {

    private static UIMenu mainMenu;
    private static Menu menu;

    public static void main(String[] args) {
        menu = new Menu();

        mainMenu = new UIMenu("Main Menu", false);

        mainMenu.addOption(1, new UIMenuOption("Menu", () -> {
            mainMenu.goToMenu(menu.getMenuMenu());
        }, false));

        mainMenu.open();
    }
}