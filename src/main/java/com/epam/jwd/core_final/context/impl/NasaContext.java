package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.ArgumentNotFoundException;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.util.FilesInfoManipulator;
import com.epam.jwd.core_final.util.PropertyReaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

// todo
public class NasaContext implements ApplicationContext{

    private static final Logger LOGGER = LoggerFactory.getLogger(NasaContext.class);
    private static NasaContext context;

    // no getters/setters for them
    private final Collection<CrewMember> crewMembers = new ArrayList<>();
    private final Collection<Spaceship> spaceships = new ArrayList<>();
    private final Collection<FlightMission> flightMissions = new ArrayList<>();

    private NasaContext(){
    }

    public static NasaContext getContext(){
        if (context == null){
            context = new NasaContext();
        }
        return context;
    }

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) throws UnknownEntityException{
        Collection<T> neededList;
        if (tClass == CrewMember.class){
            neededList = (Collection<T>) crewMembers;
        } else if (tClass == Spaceship.class){
            neededList = (Collection<T>) spaceships;
        } else if (tClass == FlightMission.class){
            neededList = (Collection<T>) flightMissions;
        } else {
            throw new UnknownEntityException(tClass.getCanonicalName());
        }
        return neededList;
    }

    /**
     * You have to read input files, populate collections
     *
     * @throws InvalidStateException
     */
    @Override
    public void init() throws InvalidStateException{
        try{
            populateCrewMembersList(ApplicationProperties.getApplicationProperties().getCrewFileName());
            populateSpaceshipsList(ApplicationProperties.getApplicationProperties().getSpaceshipsFileName());
            populateMissionsList(ApplicationProperties.getApplicationProperties().getMissionsFileName());
        } catch (IOException | ArgumentNotFoundException e){
            LOGGER.error(e.getLocalizedMessage());
            throw new InvalidStateException("TODO");
        }
    }

    private void populateCrewMembersList(String path) throws IOException, InvalidStateException, ArgumentNotFoundException{
        EntityFactory<CrewMember> factory = CrewMemberFactory.getFactory();
        List<String> crewData = FilesInfoManipulator.readFile(path)
                .stream()
                .filter(s -> (s.charAt(0) != '#'))
                .flatMap((s) -> Arrays.stream(s.split(";")))
                .collect(Collectors.toList());
        for (String crewDatum : crewData){
            List<String> params = FilesInfoManipulator.separateString(crewDatum, ",");
            crewMembers.add(factory.create(params.toArray()));
        }
    }

    private void populateSpaceshipsList(String path) throws IOException, InvalidStateException, ArgumentNotFoundException{
        EntityFactory<Spaceship> factory = SpaceshipFactory.getFactory();
        List<String> spaceshipData = FilesInfoManipulator.readFile(path)
                .stream()
                .filter(s -> (s.charAt(0) != '#'))
                .collect(Collectors.toList());
        for (String spaceshipDatum : spaceshipData){
            List<String> params = FilesInfoManipulator.separateString(spaceshipDatum, ";");
            spaceships.add(factory.create(params.toArray()));
        }
    }

    private void populateMissionsList(String path) throws IOException, InvalidStateException, ArgumentNotFoundException{
        EntityFactory<FlightMission> factory = FlightMissionFactory.getFactory();
        List<String> spaceshipData = FilesInfoManipulator.readFile(path)
                .stream()
                .filter(s -> (s.charAt(0) != '#'))
                .collect(Collectors.toList());
        for (String spaceshipDatum : spaceshipData){
            List<String> params = FilesInfoManipulator.separateString(spaceshipDatum, ";");
            flightMissions.add(factory.create(params.toArray()));
        }
    }
}
