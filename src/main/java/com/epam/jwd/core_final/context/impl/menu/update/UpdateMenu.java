package com.epam.jwd.core_final.context.impl.menu.update;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class UpdateMenu{
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateMenu.class);
    private static final Scanner SCANNER = new Scanner(System.in);
    private static UpdateMenu updateMenu;

    private UpdateMenu(){
    }

    public static UpdateMenu getUpdateMenu(){
        if (updateMenu == null){
            updateMenu = new UpdateMenu();
        }
        return updateMenu;
    }

    private void printAvailableOptions(){
        System.out.println("Update options: ");
        System.out.println("c -> Update Crew Member");
      //  System.out.println("s -> Update Spaceship");
       // System.out.println("f -> Update Flight Mission");
        System.out.println("b -> Back");
    }

    public void updateMenu(){
        System.out.println("---------------------------------");
        printAvailableOptions();
        System.out.println("Select option: ");
        String str = SCANNER.nextLine();
        updateSwitch(str);
    }

    private Boolean handleUserInput(String str){
        if (str.length() == 1 || str.matches("[A-Za-z]")){
            return true;
        } else {
            System.out.println("Wrong input. Try again.");
            return false;
        }
    }

    private void updateSwitch(String str){
        String result = waitingRightSymbol(str);
        switch (result){
            case "b":
                break;
//            case "s":
//
//                break;
//            case "f":
//
//                break;
            case "c":
                LOGGER.info("Update CrewMember");
                UpdateCrewMember.getUpdateCrewMember().update();
                break;
            default:
                System.out.println("Wrong symbol.");
                break;
        }
    }

    private String waitingRightSymbol(String str){
        boolean end = false;
        do{
            if (handleUserInput(str)){
                end = true;
            } else {
                str = SCANNER.nextLine();
            }
        }while (!end);
        return str;
    }
}
