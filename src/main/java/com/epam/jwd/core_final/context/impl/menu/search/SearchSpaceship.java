package com.epam.jwd.core_final.context.impl.menu.search;

import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.impl.SimpleSpaceshipService;
import com.epam.jwd.core_final.util.JSONOutPut;

import java.util.List;
import java.util.Scanner;

public class SearchSpaceship{

    private static SearchSpaceship searchSpaceship;
    private static final SimpleSpaceshipService SPACESHIP_SERVICE = SimpleSpaceshipService.getSpaceshipService();
    private static final Scanner SCANNER = new Scanner(System.in);

    private SearchSpaceship(){
    }

    public static SearchSpaceship getSearchSpaceship(){
        if (searchSpaceship == null){
            searchSpaceship = new SearchSpaceship();
        }
        return searchSpaceship;
    }

    public void search(){
        System.out.println("---------------------------------");
        System.out.println("Search params: ");
        List<Spaceship> list = SPACESHIP_SERVICE.findAllSpaceshipsByCriteria(takeCriteria());
        for (Spaceship ship : list){
            JSONOutPut.output(ship);
            System.out.println("Ship: " + ship.getName() + " id: " + ship.getId());
        }
    }

    private Criteria<Spaceship> takeCriteria(){
        SpaceshipCriteria.SpaceshipCriteriaBuilder builder = SpaceshipCriteria.builder();
        if (includeField("id")){
            takeIDFromInput(builder);
        }
        if (includeField("name")){
            takeNameFromInput(builder);
        }
        if (includeField("not in mission")){
            takeNotOnMissionFromInput(builder);
        }
        if (includeField("is broken")){
            takeIsAliveFromInput(builder);
        }
        if (includeField("distance")){
            takeDistanceFromInput(builder);
        }  if (includeField("crew")){
            takeCrewFromInput(builder);
        }
        return builder.build();
    }

    private void takeIDFromInput(SpaceshipCriteria.SpaceshipCriteriaBuilder builder){
        System.out.print("Enter id: ");
        String str = SCANNER.nextLine();
        if (!str.matches("[0-9]+")){
            builder.id(null);
        } else {
            builder.id(Long.parseLong(str));
        }
    }

    private void takeDistanceFromInput(SpaceshipCriteria.SpaceshipCriteriaBuilder builder){
        System.out.print("Enter distance: ");
        String str = SCANNER.nextLine();
        if (!str.matches("[0-9]+")){
            builder.flightDistance(null);
        } else {
            builder.flightDistance(Long.parseLong(str));
        }
    }

    private void takeNameFromInput(SpaceshipCriteria.SpaceshipCriteriaBuilder builder){
        System.out.print("Enter name: ");
        String str = SCANNER.nextLine();
        if (!str.matches("[A-Za-z ]+")){
            builder.name(null);
        } else {
            builder.name(str);
        }
    }

    private void takeCrewFromInput(SpaceshipCriteria.SpaceshipCriteriaBuilder builder){
        System.out.print("Enter crew(format \"roleid:count,roleid:count,roleid:count,roleid:count\": ");
        String str = SCANNER.nextLine();
        if (!str.matches("[0-9]:[0-9],[0-9]:[0-9],[0-9]:[0-9],[0-9]:[0-9]")){
            builder.crew(null);
        } else {
            builder.crew(SpaceshipFactory.getFactory().takeCrewFromString(str));
        }
    }

    private void takeNotOnMissionFromInput(SpaceshipCriteria.SpaceshipCriteriaBuilder builder){
        System.out.print("Enter is on mission(true/false): ");
        String str = SCANNER.nextLine();
        if (!(str.matches("true") || str.matches("false"))){
            builder.notOnMission(null);
        } else {
            builder.notOnMission(Boolean.parseBoolean(str));
        }
    }

    private void takeIsAliveFromInput(SpaceshipCriteria.SpaceshipCriteriaBuilder builder){
        System.out.print("Enter is on mission(true/false): ");
        String str = SCANNER.nextLine();
        if (!(str.matches("true") || str.matches("false"))){
            builder.readyForNextMissions(null);
        } else {
            builder.readyForNextMissions(Boolean.parseBoolean(str));
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
