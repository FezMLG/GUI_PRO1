package com.fezmlg.staff.cook;

import java.util.ArrayList;
import java.util.List;

public class CookController {

    private ArrayList<Cook> cookArrayList;

    public CookController() {
        this.cookArrayList = new ArrayList<>();
    }

    public void addToList(Cook... cooks) {
        this.cookArrayList.addAll(List.of(cooks));
    }

    public void removeFromList(Cook cook) {
        this.cookArrayList.removeIf(n -> n == cook);
    }

    public ArrayList<Cook> getCookArrayList() {
        return cookArrayList;
    }

    public void setCookArrayList(ArrayList<Cook> cookArrayList) {
        this.cookArrayList = cookArrayList;
    }
}
