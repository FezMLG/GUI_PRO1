package com.fezmlg.restaurant.kitchen;

public class Worker implements Runnable {
    public static void main(String[] args) {
        Worker kitchen = new Worker();
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
