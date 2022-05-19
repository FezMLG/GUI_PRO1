package com.fezmlg.restaurant.kitchen;

import java.util.concurrent.TimeUnit;

public class Kitchen implements Runnable {
    public static void main(String[] args) {
        Kitchen kitchen = new Kitchen();
        Thread thread = new Thread(kitchen);
        thread.start();
        while (thread.isAlive()) {
            System.out.println("Waiting...");
        }
        System.out.println("This code is outside of the thread");
    }

    public void run() {
        System.out.println("This code is running in a thread");
    }
}
