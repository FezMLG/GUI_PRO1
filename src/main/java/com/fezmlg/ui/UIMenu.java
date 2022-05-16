package com.fezmlg.ui;

import com.fezmlg.menu.Menu;
import com.fezmlg.menu.MenuItem;

import java.util.*;

public class UIMenu {

    private HashMap<Integer, UIMenuOption> listOfOptions = new HashMap<>();
    private String menuTitle;
    private boolean subMenu;
    private Integer selectedOption;

    public UIMenu(UIMenuOption... options) {
//        this.listOfOptions.addAll(Arrays.asList(options));
    }

    public UIMenu(String menuTitle, boolean subMenu){
        this.menuTitle = menuTitle;
        this.listOfOptions = new HashMap<>();
        this.subMenu = subMenu;
        this.selectedOption = -2;
    }

    public void show(){
        UI ui = new UI();
        for (Map.Entry<Integer, UIMenuOption> entry : listOfOptions.entrySet()) {
            ui.print(String.valueOf(entry.getKey()));
            ui.println(entry.getValue().getDescription());
        }
    }

    public void addOption(int optionNumber, UIMenuOption option){
        listOfOptions.put(optionNumber, option);
    }

//    public void showAndChoose(){
//        UI ui = new UI();
//        UIMenu uiMenu = new UIMenu();
//        for (UIMenuOption option : listOfOptions) {
//            ui.print(String.valueOf(option.getOrder()), " ");
//            ui.println(option.getDescription());
//        }
//        int key = ui.listenForKey();
//        this.listOfOptions.removeIf(n -> n.getOrder() != key);
//        ui.println("Chosen option:");
//        for (UIMenuOption option : listOfOptions) {
//            ui.print(String.valueOf(option.getOrder()));
//            ui.println(option.getDescription());
//        }
//    }

    public int open(){
        Scanner scanner = new Scanner(System.in);

        while(selectedOption != 0 && selectedOption != -1){
            System.out.println("");
            System.out.println("----------------------");
            System.out.println("   " + menuTitle + "   ");
            System.out.println("----------------------");
            System.out.println("");
            for(int number : listOfOptions.keySet()){
                System.out.println(number + ". " + listOfOptions.get(number).getDescription());
            }
            if(subMenu){
                System.out.println("0. Back");
                System.out.println("-1. Exit");
            }
            else{
                System.out.println("0. Exit");
            }
            System.out.println("");
            System.out.print("Choose option: ");

            try{
                String text = scanner.nextLine();
                selectedOption = Integer.parseInt(text);
            }
            catch (NumberFormatException e){
                System.out.println("Enter valid option!");
                continue;
            }

//            clearScreen();

            if(listOfOptions.containsKey(selectedOption)){
                UIMenuOption option = listOfOptions.get(selectedOption);
                option.getRunnable().run();
                if(option.isReturnAfterAction()){
                    selectedOption = 0;
                }
            }
        }
        return selectedOption;
    }

    public void goToMenu(UIMenu menu){
        if(menu != null){
            int result = menu.open();
            if(result == -1){
                listOfOptions.get(selectedOption).setReturnAfterAction(false);
                selectedOption = result;
            }
        }
    }

    private void clearScreen(){
        for(int i = 0; i < 60; i++){
            System.out.println("");
        }
    }

//    public int showAndChoose(){
//        UI ui = new UI();
//        UIMenu uiMenu = new UIMenu();
//        for (UIMenuOption option : listOfOptions) {
//            ui.print(String.valueOf(option.getOrder()), " ");
//            ui.println(option.getDescription());
//        }
//        int key = ui.listenForKey();
//        return key;
//    }
}
