package com.fezmlg.utils;

import java.util.UUID;

public class IDGenerator {
    public IDGenerator() {
    }

    public UUID getID(){
        return UUID.randomUUID();
    }
}

