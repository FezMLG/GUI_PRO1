package com.fezmlg.ui;

import java.util.*;

public class UIMenu {

    private final HashMap<Integer, UIMenuOption> listOfOptions;
    private final ArrayList<String> listOfText;
    private final String menuTitle;
    private final boolean subMenu;
    private Integer selectedOption;

    public UIMenu(String menuTitle, boolean subMenu) {
        this.menuTitle = menuTitle;
        this.subMenu = subMenu;
        this.selectedOption = -2;

        this.listOfOptions = new HashMap<>();
        listOfText = new ArrayList<>();
    }

    public void addOption(int optionNumber, UIMenuOption option) {
        listOfOptions.put(optionNumber, option);
    }

    public void addText(String... textToAdd) {
        StringBuilder toAdd = new StringBuilder();
        for (String text :
                textToAdd) {
            toAdd.append(text);
            toAdd.append("\n");
        }
        listOfText.add(toAdd.toString());
    }

    public String multiLineBuilder(String... textToAdd) {
        StringBuilder toAdd = new StringBuilder();
        for (String text :
                textToAdd) {
            toAdd.append(text);
            toAdd.append("\n");
        }
        return toAdd.toString();
    }

    public int open() {
        Scanner scanner = new Scanner(System.in);

        while (selectedOption != 0 && selectedOption != -1) {
            System.out.println("");
            System.out.println("----------------------");
            System.out.println("   " + menuTitle + "   ");
            System.out.println("----------------------");
            System.out.println("");
            for (String text : listOfText) {
                System.out.println(text);
                System.out.println("----------------------");
            }
            for (int number : listOfOptions.keySet()) {
                System.out.println(number + ". " + listOfOptions.get(number).getDescription());
            }
            if (subMenu) {
                System.out.println("0. Back");
                System.out.println("-1. Quit");
            } else {
                System.out.println("0. Back");
            }
            System.out.println("");
            System.out.print("Choose option: ");

            try {
                String text = scanner.nextLine();
                selectedOption = Integer.parseInt(text);
            } catch (NumberFormatException e) {
                System.out.println("Enter valid option!");
                continue;
            }

            clearScreen();

            if (listOfOptions.containsKey(selectedOption)) {
                UIMenuOption option = listOfOptions.get(selectedOption);
                option.getRunnable().run();
                if (option.isReturnAfterAction()) {
                    selectedOption = 0;
                }
            }
        }
        return selectedOption;
    }

    public void goToMenu(UIMenu menu) {
        if (menu != null) {
            int result = menu.open();
            if (result == -1) {
                listOfOptions.get(selectedOption).setReturnAfterAction(false);
                selectedOption = result;
            }
        }
    }

    private void clearScreen() {
        for (int i = 0; i < 60; i++) {
            System.out.println("");
        }
    }

}
