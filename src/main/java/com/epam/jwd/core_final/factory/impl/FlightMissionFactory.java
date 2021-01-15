package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.domain.AbstractBaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.ArgumentNotFoundException;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.service.impl.SimpleCrewService;
import com.epam.jwd.core_final.service.impl.SimpleSpaceshipService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlightMissionFactory implements EntityFactory<FlightMission>{

    private static FlightMissionFactory factory;

    private FlightMissionFactory(){
    }

    public static FlightMissionFactory getFactory(){
        if (factory == null){
            factory = new FlightMissionFactory();
        }
        return factory;
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

    private FlightMission formingMission(Object... args) throws ArgumentNotFoundException, InvalidStateException{
        FlightMission initialized = initMission(args);
        SimpleSpaceshipService.getSpaceshipService().assignSpaceshipOnMission(initialized);
        assignCrew(initialized);
        MissionResult.assignMissionResult(initialized);
        return initialized;
    }

    private void assignCrew(FlightMission mission){
        List<CrewMember> crew = new ArrayList<>();
        for (int i = 1; i <= mission.getSpaceship().getCrew().size(); i++){
            Role role = Role.resolveRoleById((long) i);
            crew.addAll(searchMembersWithParams(role, mission.getSpaceship().getCrew().get(role)));
        }
        for (CrewMember member : crew){
            SimpleCrewService.getCrewService().assignCrewMemberOnMission(member);
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

    private FlightMission initMission(Object... args){
        String name = (String) args[0];
        LocalDate startDate = takeDateFromString((String) args[1]);
        LocalDate endDate = takeDateFromString((String) args[2]);
        Long distance = Long.parseLong((String) args[3]);
        return new FlightMission(name, startDate, endDate, distance, AbstractBaseEntity.reserveID());
    }

    public LocalDate takeDateFromString(String str){
        List<String> ymd = Arrays.stream(str.substring(0, 10).split("-")).collect(Collectors.toList());
        return LocalDate.of(Integer.parseInt(ymd.get(0)),
                Integer.parseInt(ymd.get(1)), Integer.parseInt(ymd.get(2)));
    }
}
