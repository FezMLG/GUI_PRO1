package com.fezmlg.ui;

import java.util.Objects;
import java.util.Scanner;

public class UI {
    public UI() {

    }

    public void println(String... toPrint){
        for (String line : toPrint) {
            System.out.println(line);
        }
    }

    public void print(String... toPrint){
        for (String line : toPrint) {
            System.out.print(line);
        }
    }

    public int listenForKey(){
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Choose option:");

        int key = Integer.parseInt(scanner.nextLine());
        return key;
    }

    public String listenForInput(){
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String input = scanner.nextLine();
        return input;
    }

    public boolean listenForAcceptance(String yes, String no){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
//        while(!Objects.equals(input, yes) || !Objects.equals(input, no)){
//            println("Invalid character");
//            input = scanner.nextLine();
//        }

        return (Objects.equals(input, yes));
    }

}
