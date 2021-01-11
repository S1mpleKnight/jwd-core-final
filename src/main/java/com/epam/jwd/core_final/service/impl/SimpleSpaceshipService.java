package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.ArgumentNotFoundException;
import com.epam.jwd.core_final.service.SpaceshipService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SimpleSpaceshipService implements SpaceshipService{

    private static SimpleSpaceshipService spaceshipService;

    private SimpleSpaceshipService(){
    }

    public static SimpleSpaceshipService getSpaceshipService(){
        if (spaceshipService == null){
            spaceshipService = new SimpleSpaceshipService();
        }
        return spaceshipService;
    }

    @Override
    public List<Spaceship> findAllSpaceships(){
        return new ArrayList<>(NasaContext.getContext().retrieveBaseEntityList(Spaceship.class));
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria){
        SpaceshipCriteria spaceshipCriteria = (SpaceshipCriteria) criteria;
        return NasaContext.getContext().retrieveBaseEntityList(Spaceship.class)
                .stream()
                .filter(spaceship -> spaceshipCriteria.getName() == null
                        || spaceship.getName().equals(spaceshipCriteria.getName()))
                .filter(spaceship -> spaceshipCriteria.getId() == null
                        || spaceship.getId().equals(spaceshipCriteria.getId()))
                .filter(spaceship -> spaceshipCriteria.getNotOnMission() == null
                        || spaceship.getNotOnMission().equals(spaceshipCriteria.getNotOnMission()))
                .filter(spaceship -> spaceshipCriteria.getReadyForNextMissions() == null
                        || spaceship.getReadyForNextMissions().equals(spaceshipCriteria.getReadyForNextMissions()))
                .filter(spaceship -> spaceshipCriteria.getFlightDistance() == null
                        || spaceship.getFlightDistance().equals(spaceshipCriteria.getFlightDistance()))
                .filter(spaceship -> spaceshipCriteria.getCrew() == null
                        || spaceship.getCrew().entrySet().containsAll(spaceshipCriteria.getCrew().entrySet()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria){
        return findAllSpaceshipsByCriteria(criteria).stream().findFirst();
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship, Long flightDistance){
        spaceship.setFlightDistance(spaceship.getFlightDistance() + flightDistance);
        return spaceship;
    }

    @Override
    public void assignSpaceshipOnMission(FlightMission mission) throws ArgumentNotFoundException{
        Spaceship spaceship = searchSpaceship(mission);
        spaceship.setNotOnMission(false);
        mission.setSpaceship(spaceship);
    }

    @Override
    public Spaceship createSpaceship(Spaceship spaceship) throws RuntimeException{
        long count = NasaContext.getContext().retrieveBaseEntityList(Spaceship.class)
                .stream()
                .filter(ship -> spaceship.getName().equals(ship.getName()))
                .count();
        if (count != 0){
            return null;
        } else {
            return spaceship;
        }
    }

    private Spaceship searchSpaceship(FlightMission mission) throws ArgumentNotFoundException{
        return NasaContext.getContext().retrieveBaseEntityList(Spaceship.class)
                .stream()
                .filter(Spaceship::getReadyForNextMissions)
                .filter(Spaceship::getNotOnMission)
                .findFirst().orElseThrow(ArgumentNotFoundException::new);
    }
}
