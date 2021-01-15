package com.epam.jwd.core_final.context.impl.menu.search;

import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.impl.SimpleMissionService;
import com.epam.jwd.core_final.service.impl.SimpleSpaceshipService;
import com.epam.jwd.core_final.util.JSONOutPut;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class SearchFlightMission{

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final SimpleMissionService MISSION_SERVICE = SimpleMissionService.getMissionService();
    private static SearchFlightMission searchFlightMission;

    private SearchFlightMission(){
    }

    public static SearchFlightMission getSearchFlightMission(){
        if (searchFlightMission == null){
            searchFlightMission = new SearchFlightMission();
        }
        return searchFlightMission;
    }


    public void search(){
        System.out.println("---------------------------------");
        System.out.println("Search params: ");
        List<FlightMission> list = MISSION_SERVICE.findAllMissionsByCriteria(takeCriteria());
        for (FlightMission mission : list){
            JSONOutPut.output(mission);
            System.out.println("Ship: " + mission.getName() + " id: " + mission.getId());
        }
    }

    private Criteria<FlightMission> takeCriteria(){
        FlightMissionCriteria.FlightMissionCriteriaBuilder builder = FlightMissionCriteria.builder();
        if (includeField("id")){
            takeIDFromInput(builder);
        }
        if (includeField("name")){
            takeNameFromInput(builder);
        }
        if (includeField("start date")){
            takeDateFromInput(builder, true);
        }
        if (includeField("end date")){
            takeDateFromInput(builder, false);
        }
        if (includeField("distance")){
            takeDistanceFromInput(builder);
        }
        if (includeField("spaceship")){
            takeSpaceshipFromInput(builder);
        }
        if (includeField("mission result")){
            takeMissionResultFromInput(builder);
        }
        return builder.build();
    }

    private void takeIDFromInput(FlightMissionCriteria.FlightMissionCriteriaBuilder builder){
        System.out.print("Enter id: ");
        String str = SCANNER.nextLine();
        if (!str.matches("[0-9]+")){
            builder.id(null);
        } else {
            builder.id(Long.parseLong(str));
        }
    }

    private void takeDistanceFromInput(FlightMissionCriteria.FlightMissionCriteriaBuilder builder){
        System.out.print("Enter distance: ");
        String str = SCANNER.nextLine();
        if (!str.matches("[0-9]+")){
            builder.distance(null);
        } else {
            builder.distance(Long.parseLong(str));
        }
    }

    private void takeNameFromInput(FlightMissionCriteria.FlightMissionCriteriaBuilder builder){
        System.out.print("Enter name: ");
        String str = SCANNER.nextLine();
        if (!str.matches("[A-Za-z ]+")){
            builder.name(null);
        } else {
            builder.name(str);
        }
    }

    private void takeMissionResultFromInput(FlightMissionCriteria.FlightMissionCriteriaBuilder builder){
        System.out.print("Enter result(number): ");
        String str = SCANNER.nextLine();
        if (!str.matches("[0-9]+")){
            builder.missionResult(null);
        } else {
            if (str.charAt(0) > '4'){
                builder.missionResult(null);
            } else {
                builder.missionResult(MissionResult.values()[Integer.parseInt(str)]);
            }
        }
    }

    private void takeSpaceshipFromInput(FlightMissionCriteria.FlightMissionCriteriaBuilder builder){
        System.out.print("Enter spaceship name: ");
        String str = SCANNER.nextLine();
        if (!str.matches("[A-Za-z ]+")){
            builder.spaceship(null);
        } else {
            Optional<Spaceship> spaceship = SimpleSpaceshipService.getSpaceshipService()
                    .findSpaceshipByCriteria(new SpaceshipCriteria.SpaceshipCriteriaBuilder().name(str).build());
            if (spaceship.isEmpty()){
                builder.spaceship(null);
            } else {
                builder.spaceship(spaceship.get());
            }
        }
    }

    private void takeDateFromInput(FlightMissionCriteria.FlightMissionCriteriaBuilder builder, boolean startDate){
        System.out.print("Enter date(format \"yyyy-mm-dd\": ");
        String str = SCANNER.nextLine();
        if (str.length() != 10){
            if (startDate){
                builder.startDate(null);
            } else {
                builder.endDate(null);
            }
        } else {
            if (startDate){
                builder.startDate(FlightMissionFactory.getFactory().takeDateFromString(str));
            } else {
                builder.endDate(FlightMissionFactory.getFactory().takeDateFromString(str));
            }
        }
    }

    private boolean includeField(String str){
        System.out.print("Include field " + str + "(y/n): ");
        String answer;
        boolean statement = true;
        do{
            answer = SCANNER.nextLine();
            if (answer.equals("y") || answer.equals("n")){
                statement = false;
            } else {
                System.out.print("Try again: ");
                answer = SCANNER.nextLine();
            }
        }while (statement);
        return answer.equals("y");
    }
}
