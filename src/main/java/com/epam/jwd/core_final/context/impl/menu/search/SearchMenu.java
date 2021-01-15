package com.epam.jwd.core_final.context.impl.menu.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class SearchMenu{
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchMenu.class);
    private static final Scanner SCANNER = new Scanner(System.in);
    private static SearchMenu searchMenu;

    private SearchMenu(){
    }

    public static SearchMenu getSearchMenu(){
        if (searchMenu == null){
            searchMenu = new SearchMenu();
        }
        return searchMenu;
    }

    private void printAvailableOptions(){
        System.out.println("Search options: ");
        System.out.println("c -> Search Crew Member");
        System.out.println("s -> Search Spaceship");
       // System.out.println("f -> Search Flight Mission");
        System.out.println("b -> Back");
    }

    public void searchMenu(){
        System.out.println("---------------------------------");
        printAvailableOptions();
        System.out.println("Select option: ");
        String str = SCANNER.nextLine();
        searchSwitch(str);
    }

    private Boolean handleUserInput(String str){
        if (str.length() == 1 || str.matches("[A-Za-z]")){
            return true;
        } else {
            System.out.println("Wrong input. Try again.");
            return false;
        }
    }

    private void searchSwitch(String str){
        String result = waitingRightSymbol(str);
        switch (result){
            case "b":
                break;
            case "s":
                LOGGER.info("Search Spaceship");
                SearchSpaceship.getSearchSpaceship().search();
                break;
//            case "f":
//                LOGGER.info("Search FlightMission");
//
//                break;
            case "c":
                LOGGER.info("Search CrewMember");
                SearchCrewMember.getSearchCrewMember().search();
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
