package com.epam.jwd.core_final.context.impl.menu.insertion;

import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.exception.ArgumentNotFoundException;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.impl.SimpleMissionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InsertFlightMission{

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final SimpleMissionService MISSION_SERVICE = SimpleMissionService.getMissionService();
    private static InsertFlightMission insertFlightMission;

    private InsertFlightMission(){
    }

    public static InsertFlightMission getInsertFlightMission(){
        if (insertFlightMission == null){
            insertFlightMission = new InsertFlightMission();
        }
        return insertFlightMission;
    }

    public void insert() throws InvalidStateException, ArgumentNotFoundException{
        List<String> list = takeArgs();
        Boolean check = checkInput(list);
        if (!check){
            throw new InvalidStateException("Invalid creating data");
        } else {
            FlightMission mission = FlightMissionFactory.getFactory().create(list.toArray());
            mission = MISSION_SERVICE.createMission(mission);
            if (mission == null){
                System.out.println("Mission with this name is already exist");
            } else {
                NasaContext.getContext().addFlightMission(mission);
            }
        }
    }

    private List<String> takeArgs(){
        List<String> args = new ArrayList<>();
        System.out.println("---------------------------------");
        System.out.println("Flight Mission creation");
        System.out.print("Enter name: ");
        args.add(SCANNER.nextLine());
        System.out.print("Enter start date(format \"yyyy-mm-dd\"): ");
        args.add(SCANNER.nextLine());
        System.out.print("Enter end date(format \"yyyy-mm-dd\"): ");
        args.add(SCANNER.nextLine());
        System.out.print("Enter destination: ");
        args.add(SCANNER.nextLine());
        return args;
    }

    private Boolean checkInput(List<String> args){
        if (!args.get(0).matches("[A-Za-z ]+")){
            return false;
        } else if ((args.get(1).length() != 10) || !args.get(1).matches("[[0-9]-]+")){
            return false;
        } else if ((args.get(2).length() != 10) || !args.get(2).matches("[[0-9]-]+")){
            return false;
        } else return args.get(3).matches("[0-9]+");
    }
}
