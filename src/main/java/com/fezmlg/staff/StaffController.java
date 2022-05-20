package com.fezmlg.staff;

import com.fezmlg.staff.cook.Cook;
import com.fezmlg.staff.delivery.Delivery;
import com.fezmlg.staff.waiter.Waiter;
import com.fezmlg.ui.UI;
import com.fezmlg.ui.UIMenu;
import com.fezmlg.ui.UIMenuOption;
import com.fezmlg.utils.JSONSaver;

import java.util.ArrayList;
import java.util.List;

public class StaffController {

    private ArrayList<Staff> staffArrayList;
    private final UI ui;

    public StaffController() {
        this.staffArrayList = new ArrayList<>();
        ui = new UI();
    }

    public UIMenu uiMainMenu() {
        UIMenu uiMenu = new UIMenu("Staff", false);

        uiMenu.addOption(1, new UIMenuOption("Show staff and manage it", () -> uiMenu.goToMenu(this.uiGetStaffMembers()), false));
        uiMenu.addOption(2, new UIMenuOption("Save staff", this::save, false));
        uiMenu.addOption(3, new UIMenuOption("Load staff", this::load, false));
        uiMenu.addOption(4, new UIMenuOption("Add staff member", this::staffMaker, false));

        return uiMenu;
    }

    public UIMenu uiGetStaffMembers() {
        UIMenu uiMenu = new UIMenu("Staff List", false);
        int i = 1;
        for (Staff staff : staffArrayList) {
            uiMenu.addOption(i, new UIMenuOption(staff.getStaffType() + " - " + staff.getName(), () -> uiMenu.goToMenu(this.uiGetStaffMember(staff)), true));
            i++;
        }

        return uiMenu;
    }

    public UIMenu uiGetStaffMember(Staff staff) {
        UIMenu uiMenu = new UIMenu(staff.getStaffType() + " - " + staff.getName(), false);

        uiMenu.addText(
                "ID: " + staff.getId(),
                "Type: " + staff.getStaffType(),
                "Name: " + staff.getName(),
                "Number: " + staff.getNumber(),
                "Made orders: " + staff.getMadeOrders(),
                "Tips: " + staff.getTips()
        );
        uiMenu.addOption(1, new UIMenuOption("Remove staff member", () -> {
            try {
                this.removeStaffMember(staff);
            } catch (Exception e) {
                System.out.println("Low on staff with role: " + staff.getStaffType());
            }
        }, true));

        return uiMenu;
    }

    private void staffMaker() {
        ui.println("Name: ");
        String name = ui.listenForInput();
        ui.println("Phone number: ");
        String phone = ui.listenForInput();
        ui.println("Select staff type:");
        ui.println("1 - Cook, 2 - Waiter, 3 - Delivery");
        int staffTypeChoose = ui.listenForKey();
        int size = generateID();
        if (staffTypeChoose == 1) {
            this.staffArrayList.add(new Cook(size, name, phone));
        } else if (staffTypeChoose == 2) {
            this.staffArrayList.add(new Waiter(size, name, phone));
        } else if (staffTypeChoose == 3) {
            this.staffArrayList.add(new Delivery(size, name, phone));
        }
    }

    private void removeStaffMember(Staff staff) {
        this.staffArrayList.removeIf(n -> n == staff);
        ArrayList<Staff> localCopy = this.staffArrayList;
        localCopy.removeIf(n -> n.getStaffType() != staff.getStaffType());
        try {
            if (localCopy.size() == 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println(
                    "********************" +
                            "Low on staff with role: " + staff.getStaffType() +
                            "********************"
            );
        }
    }

    public int generateID() {
        if (staffArrayList == null) return 1;
        return this.staffArrayList.size() + 1;
    }

    public void addToList(Staff... staffs) {
        this.staffArrayList.addAll(List.of(staffs));
    }

    public void removeFromList(Staff staff) {
        this.staffArrayList.removeIf(n -> n == staff);
    }

    public void save() {
        new JSONSaver().saveToFile(staffArrayList, "staffController");
    }

    public void load() {
        ArrayList<Staff> data = new JSONSaver().loadFromFileStaff("staffController");
        this.setStaffArrayList(data);
    }

    public ArrayList<Staff> getStaffArrayList() {
        return staffArrayList;
    }

    public void setStaffArrayList(ArrayList<Staff> staffArrayList) {
        if (staffArrayList == null) return;
        this.staffArrayList = staffArrayList;
    }
}
