package com.fezmlg;

import com.fezmlg.menu.Menu;
import com.fezmlg.menu.MenuItem;
import com.fezmlg.ui.UI;
import com.fezmlg.ui.UIMenu;
import com.fezmlg.ui.UIMenuOption;

public class Main {

    private static UIMenu mainMenu;
    private static Menu menu;

    public static void main(String[] args) {
        menu = new Menu();
        loadDemo();
        mainMenu = new UIMenu("Main Menu", false);

        mainMenu.addOption(1, new UIMenuOption("Menu", () -> {
            mainMenu.goToMenu(menu.getMenuMenu());
        }, false));

        mainMenu.open();
    }

    public static void loadDemo(){
        menu.addToMenu(new MenuItem("Item 1", "desc for item 1", 1 ,true ));
        menu.addToMenu(new MenuItem("Item 2", "desc for item 2", 2 ,true ));
        menu.addToMenu(new MenuItem("Item 3", "desc for item 3", 3 ,true ));
    }
}