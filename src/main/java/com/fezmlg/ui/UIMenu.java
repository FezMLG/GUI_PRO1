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

    //TODO printing options
}
