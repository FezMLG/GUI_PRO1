package com.fezmlg.ui;

public class UIMenuOption {
    private int order;
    private String description;

    public UIMenuOption(int number, String desc) {
        this.order = number;
        this.description = desc;
    }

    public int getOrder() {
        return order;
    }

    public String getDescription() {
        return description;
    }
}
