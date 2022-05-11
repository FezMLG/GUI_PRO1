package com.fezmlg.ui;

import com.fezmlg.menu.Menu;
import com.fezmlg.menu.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;

public class UIMenu {

    private ArrayList<UIMenuOption> listOfOptions = new ArrayList<>();

    public UIMenu(UIMenuOption... options) {
        this.listOfOptions.addAll(Arrays.asList(options));
    }

    public void show(){
        UI ui = new UI();
        for (UIMenuOption option : listOfOptions) {
            ui.print(String.valueOf(option.getOrder()));
            ui.println(option.getDescription());
        }
    }

    public void showAndChoose(){
        UI ui = new UI();
        UIMenu uiMenu = new UIMenu();
        for (UIMenuOption option : listOfOptions) {
            ui.print(String.valueOf(option.getOrder()));
            ui.println(option.getDescription());
        }
        int key = ui.listenForKey();
        this.listOfOptions.removeIf(n -> n.getOrder() != key);
        ui.println("Chosen option:");
        for (UIMenuOption option : listOfOptions) {
            ui.print(String.valueOf(option.getOrder()));
            ui.println(option.getDescription());
        }
    }
}
