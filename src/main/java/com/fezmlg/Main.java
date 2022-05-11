package com.fezmlg;

import com.fezmlg.ui.UI;
import com.fezmlg.ui.UIMenu;
import com.fezmlg.ui.UIMenuOption;

public class Main {
    public static void main(String[] args) {
        UIMenuOption uiMenuOption = new UIMenuOption(1, " Opcja 1");
        UIMenuOption uiMenuOption2 = new UIMenuOption(2, " Opcja 2");
        UIMenu uiMenu = new UIMenu(uiMenuOption, uiMenuOption2);
        uiMenu.showAndChoose();
    }
}