package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlightMissionFactory implements EntityFactory<FlightMission>{

    private static FlightMissionFactory factory;

    private FlightMissionFactory(){
    }

    @Override
    public FlightMission create(Object... args) throws InvalidStateException{
        if (args == null){
            throw new InvalidStateException("null on FlightMissionFactory");
        } else if (args.length < 3){
            throw new InvalidStateException("null on FlightMissionFactory");
        } else {
            String name = (String) args[0];
            LocalDate startDate = takeDateFromString((String) args[1]);
            LocalDate endDate =  takeDateFromString((String) args[2]);
            Long distance = Long.parseLong((String) args[3]);
            return new FlightMission(name, startDate, endDate, distance);
        }
    }

    private LocalDate takeDateFromString(String str){
        List<String> ymd = Arrays.stream(str.substring(0, 10).split("-")).collect(Collectors.toList());
        return LocalDate.of(Integer.parseInt(ymd.get(0)),
                Integer.parseInt(ymd.get(1)), Integer.parseInt(ymd.get(2)));
    }

    public static FlightMissionFactory getFactory(){
        if (factory == null){
            factory = new FlightMissionFactory();
        }
        return factory;
    }
}
