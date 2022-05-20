package com.fezmlg.ui;

import java.util.Objects;
import java.util.Scanner;

public class UI {
    public UI() {

    }

    public void println(String... toPrint) {
        for (String line : toPrint) {
            System.out.println(line);
        }
    }

    public void print(String... toPrint) {
        for (String line : toPrint) {
            System.out.print(line);
        }
    }

    public int listenForKey() {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Choose option:");

        return Integer.parseInt(scanner.nextLine());
    }

    public String listenForInput() {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        return scanner.nextLine();
    }

    public boolean listenForAcceptance(String yes) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return (Objects.equals(input, yes));
    }

}
