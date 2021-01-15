package com.epam.jwd.core_final.context.impl.menu.insertion;

import com.epam.jwd.core_final.exception.ArgumentNotFoundException;
import com.epam.jwd.core_final.exception.InvalidStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class InsertionMenu{

    private static final Logger LOGGER = LoggerFactory.getLogger(InsertionMenu.class);
    private static final Scanner SCANNER = new Scanner(System.in);
    private static InsertionMenu insertionMenu;

    private InsertionMenu(){
    }

    public static InsertionMenu getInsertionMenu(){
        if (insertionMenu == null){
            insertionMenu = new InsertionMenu();
        }
        return insertionMenu;
    }

    private void printAvailableOptions(){
        System.out.println("---------------------------------");
        System.out.println("Insertion options: ");
        System.out.println("c -> Insert Crew Member");
        System.out.println("s -> Insert Spaceship");
        System.out.println("f -> Insert Flight Mission");
        System.out.println("b -> Back");
    }

    public void insertionMenu(){
        printAvailableOptions();
        System.out.print("Select option: ");
        String str = SCANNER.nextLine();
        insertionSwitch(str);
    }

    private Boolean handleUserInput(String str){
        if (str.length() == 1 || str.matches("[A-Za-z]")){
            return true;
        } else {
            System.out.println("Wrong input. Try again.");
            return false;
        }
    }

    private void insertionSwitch(String str){
        String result = waitingRightSymbol(str);
        switch (result){
            case "b":
                break;
            case "s":
                spaceshipMenu();
                break;
            case "f":
                flightMissionMenu();
                break;
            case "c":
                crewMemberMenu();
                break;
            default:
                System.out.println("Wrong symbol.");
                break;
        }
    }

    private void crewMemberMenu(){
        LOGGER.info("CrewMember creation");
        try{
            InsertCrewMember.getInsertCrewMember().insert();
            System.out.println("Crew member was created");
        } catch (InvalidStateException e){
            LOGGER.error(e.getMessage());
        }
    }

    private void flightMissionMenu(){
        LOGGER.info("FlightMission creation");
        try{
            InsertFlightMission.getInsertFlightMission().insert();
        } catch (InvalidStateException | ArgumentNotFoundException e){
            LOGGER.error(e.getMessage());
        }
    }

    private void spaceshipMenu(){
        LOGGER.info("Spaceship creation");
        try{
            InsertSpaceship.getInsertSpaceship().insert();
        } catch (InvalidStateException e){
            LOGGER.error(e.getMessage());
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
