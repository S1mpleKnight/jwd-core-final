package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.ArgumentNotFoundException;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlightMissionFactory implements EntityFactory<FlightMission>{

    private static FlightMissionFactory factory;

    private FlightMissionFactory(){
    }

    @Override
    public FlightMission create(Object... args) throws InvalidStateException, ArgumentNotFoundException{
        if (args == null){
            throw new InvalidStateException("null on FlightMissionFactory");
        } else if (args.length < 3){
            throw new InvalidStateException("null on FlightMissionFactory");
        } else {
            return formingMission(args);
        }
    }

    public static FlightMissionFactory getFactory(){
        if (factory == null){
            factory = new FlightMissionFactory();
        }
        return factory;
    }

    private FlightMission formingMission(Object... args) throws ArgumentNotFoundException, InvalidStateException{
        FlightMission initialized = initMission(args);
        assignSpaceship(initialized);
        assignCrew(initialized);
        assignMissionResult(initialized);
        return initialized;
    }

    private void assignMissionResult(FlightMission mission) throws InvalidStateException{
        if (mission.getEndDate().isAfter(LocalDate.now())
                || mission.getStartDate().isBefore(LocalDate.now())){
            mission.setMissionResult(MissionResult.IN_PROGRESS);
        } else if (mission.getStartDate().isAfter(LocalDate.now())){
            mission.setMissionResult(MissionResult.PLANNED);
        } else {
            int factor = (int) Math.floor(Math.random() * 3);
            mission.setMissionResult(missionResultChanges(factor, mission));
        }
    }

    private MissionResult missionResultChanges(int factor, FlightMission mission) throws InvalidStateException{
        switch (factor){
            case 0:
                mission.getSpaceship().setNotOnMission(true);
                for (CrewMember member: mission.getCrew()){
                    member.setNotOnMission(true);
                }
                return MissionResult.CANCELLED;
            case 1:
                mission.getSpaceship().setNotOnMission(true);
                mission.getSpaceship().setFlightDistance(mission.getSpaceship().getFlightDistance()
                        + mission.getDistance());
                for (CrewMember member: mission.getCrew()){
                    member.setNotOnMission(true);
                }
                return MissionResult.COMPLETED;
            case 2:
                mission.getSpaceship().setNotOnMission(true);
                mission.getSpaceship().setReadyForNextMissions(false);
                for (CrewMember member: mission.getCrew()){
                    member.setNotOnMission(true);
                    member.setReadyForNextMissions(false);
                }
                return MissionResult.FAILED;
            default:
                throw new InvalidStateException("Wrong argument in switch");
        }
    }

    private void assignCrew(FlightMission mission){
        List<CrewMember> crew = new ArrayList<>();
        for (int i = 1; i <= mission.getSpaceship().getCrew().size(); i++){
            Role role = Role.resolveRoleById((long) i);
            crew.addAll(searchMembersWithParams(role, mission.getSpaceship().getCrew().get(role)));
        }
        for (CrewMember member: crew){
            member.setNotOnMission(false);
        }
        mission.setCrew(crew);
    }

    private List<CrewMember> searchMembersWithParams(Role role, Short amount){
        return NasaContext.getContext().retrieveBaseEntityList(CrewMember.class)
                .stream()
                .filter(CrewMember::getNotOnMission)
                .filter(CrewMember::getReadyForNextMissions)
                .filter(crewMember -> crewMember.getRole().equals(role))
                .limit(amount)
                .collect(Collectors.toList());
    }

    private void assignSpaceship(FlightMission mission) throws ArgumentNotFoundException{
        Spaceship spaceship = searchSpaceship(mission);
        spaceship.setNotOnMission(false);
        mission.setSpaceship(spaceship);
    }

    private Spaceship searchSpaceship(FlightMission mission) throws ArgumentNotFoundException{
        return NasaContext.getContext().retrieveBaseEntityList(Spaceship.class)
                .stream()
                .filter(Spaceship::getReadyForNextMissions)
                .filter(Spaceship::getNotOnMission)
                .findFirst().orElseThrow(ArgumentNotFoundException::new);
    }

    private FlightMission initMission(Object... args){
        String name = (String) args[0];
        LocalDate startDate = takeDateFromString((String) args[1]);
        LocalDate endDate = takeDateFromString((String) args[2]);
        Long distance = Long.parseLong((String) args[3]);
        return new FlightMission(name, startDate, endDate, distance);
    }

    private LocalDate takeDateFromString(String str){
        List<String> ymd = Arrays.stream(str.substring(0, 10).split("-")).collect(Collectors.toList());
        return LocalDate.of(Integer.parseInt(ymd.get(0)),
                Integer.parseInt(ymd.get(1)), Integer.parseInt(ymd.get(2)));
    }
}
