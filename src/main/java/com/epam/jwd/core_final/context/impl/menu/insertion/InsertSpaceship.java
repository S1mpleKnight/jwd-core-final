package com.epam.jwd.core_final.context.impl.menu.insertion;

import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.ArgumentNotFoundException;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.impl.SimpleMissionService;
import com.epam.jwd.core_final.service.impl.SimpleSpaceshipService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InsertSpaceship{
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final SimpleSpaceshipService SPACESHIP_SERVICE = SimpleSpaceshipService.getSpaceshipService();
    private static InsertSpaceship insertSpaceship;

    private InsertSpaceship(){
    }

    public static InsertSpaceship getInsertSpaceship(){
        if (insertSpaceship == null){
            insertSpaceship = new InsertSpaceship();
        }
        return insertSpaceship;
    }

    public void insert() throws InvalidStateException{
        List<String> list = takeArgs();
        Boolean check = checkInput(list);
        if (!check){
            throw new InvalidStateException("Invalid creating data");
        } else {
            Spaceship spaceship = SpaceshipFactory.getFactory().create(list.toArray());
            spaceship = SPACESHIP_SERVICE.createSpaceship(spaceship);
            if (spaceship == null){
                System.out.println("Ship with this name is already exist");
            } else {
                NasaContext.getContext().addSpaceship(spaceship);
            }
        }
    }

    private List<String> takeArgs(){
        List<String> args = new ArrayList<>();
        System.out.println("---------------------------------");
        System.out.println("Spaceship creation");
        System.out.print("Enter name: ");
        args.add(SCANNER.nextLine());
        System.out.print("Enter destination: ");
        args.add(SCANNER.nextLine());
        System.out.print("Enter crew: ");
        args.add("{" + SCANNER.nextLine().trim() + "}");
        return args;
    }

    private Boolean checkInput(List<String> args){
        if (!args.get(0).matches("[A-Za-z ]+")){
            return false;
        } else if (!args.get(1).matches("[0-9]+")){
            return false;
        } else if (!args.get(2).matches("[0-9]:[0-9],[0-9]:[0-9],[0-9]:[0-9],[0-9]:[0-9]")
                || args.get(2).length() != 15){
            return false;
        } else {
            return true;
        }
    }
}
