package com.fezmlg.staff.delivery;

import com.fezmlg.staff.Staff;
import com.fezmlg.staff.StaffType;

public class Delivery extends Staff {
    public Delivery(Integer id, String name, String number) {
        super(id, name, number, StaffType.DELIVERY);
    }
}
