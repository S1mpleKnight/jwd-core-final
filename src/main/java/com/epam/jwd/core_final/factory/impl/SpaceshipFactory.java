package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.util.HashMap;
import java.util.Map;

public class SpaceshipFactory implements EntityFactory<Spaceship>{

    private static SpaceshipFactory factory;

    private SpaceshipFactory(){
    }

    public static SpaceshipFactory getFactory(){
        if (factory == null){
            factory = new SpaceshipFactory();
        }
        return factory;
    }

    @Override
    public Spaceship create(Object... args) throws InvalidStateException{
        if (args == null){
            throw new InvalidStateException("null on SpaceshipFactory");
        } else if (args.length < 3){
            throw new InvalidStateException("null on SpaceShipFactory");
        } else {
            String name = (String) args[0];
            Long flightDistance = Long.parseLong((String) args[1]);
            Map<Role, Short> crew = takeCrewFromString((String) args[2]);
            return new Spaceship(crew, flightDistance, name);
        }
    }

    private Map<Role, Short> takeCrewFromString(String str){
        String prepareStr = str.substring(1, str.length() - 1);
        String[] units = prepareStr.split(",");
        Map<Role, Short> neededMap = new HashMap<>();
        for (String unit : units){
            neededMap.put(Role.resolveRoleById(Long.parseLong(unit.substring(0, 1))),
                    Short.parseShort(unit.substring(2)));
        }
        return neededMap;
    }
}
