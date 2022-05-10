package com.fezmlg.menu;

import com.fezmlg.utils.IDGenerator;

import java.util.UUID;

public class MenuItem {

    private UUID id;
    private String name;
    private String description;
    private double price;
    private boolean isAvailable;

    public MenuItem(String name, String description, double price, boolean isAvailable) {
        this.id = new IDGenerator().getID();
        this.name = name;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
