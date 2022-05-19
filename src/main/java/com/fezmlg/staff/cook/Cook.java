package com.fezmlg.staff.cook;

import com.fezmlg.staff.Staff;
import com.fezmlg.staff.StaffType;

public class Cook extends Staff {

    public Cook(Integer id, String name, String number) {
        super(id, name, number, StaffType.COOK);
    }


}
