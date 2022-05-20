package com.fezmlg.staff;

public class Staff {

    protected Integer id;
    protected String name;
    protected String number;
    protected StaffType staffType;
    protected Integer madeOrders;
    private double tips;
    private boolean isAvailable;

    public Staff(Integer id, String name, String number, StaffType staffType) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.staffType = staffType;
        this.madeOrders = 0;
        this.tips = 0;
        this.isAvailable = true;
    }

    public StaffType getStaffType() {
        return staffType;
    }

    public void setStaffType(StaffType staffType) {
        this.staffType = staffType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getMadeOrders() {
        return madeOrders;
    }

    public void setMadeOrders(Integer madeOrders) {
        this.madeOrders = madeOrders;
    }

    public void addMadeOrders(Integer madeOrders) {
        this.madeOrders += madeOrders;
    }

    public double getTips() {
        return tips;
    }

    public void setTips(double tips) {
        this.tips = tips;
    }

    public void addTips(double tips) {
        this.tips += tips;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
