package com.fezmlg.staff.waiter;

import com.fezmlg.staff.cook.Cook;

import java.util.ArrayList;
import java.util.List;

public class WaiterContoller {

    private ArrayList<Waiter> waiterArrayList;

    public WaiterContoller() {
        this.waiterArrayList = new ArrayList<>();
    }

    public void addToList(Waiter... waiters) {
        this.waiterArrayList.addAll(List.of(waiters));
    }

    public void removeFromList(Waiter waiter) {
        this.waiterArrayList.removeIf(n -> n == waiter);
    }

    public ArrayList<Waiter> getWaiterArrayList() {
        return waiterArrayList;
    }

    public void setWaiterArrayList(ArrayList<Waiter> waiterArrayList) {
        this.waiterArrayList = waiterArrayList;
    }
}
