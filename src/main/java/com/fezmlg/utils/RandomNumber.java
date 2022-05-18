package com.fezmlg.utils;

import java.util.Random;

public class RandomNumber {
    private final Random rand;

    public RandomNumber() {
        this.rand = new Random();
    }

    public int get(Integer min, Integer max) {
        return rand.nextInt((max - min) + 1) + min;
    }
}
