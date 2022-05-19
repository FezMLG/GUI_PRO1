package com.fezmlg.staff.waiter;

import com.fezmlg.staff.Staff;
import com.fezmlg.staff.StaffType;

public class Waiter extends Staff {
    public Waiter(Integer id, String name, String number) {
        super(id, name, number, StaffType.WAITER);
    }
}
