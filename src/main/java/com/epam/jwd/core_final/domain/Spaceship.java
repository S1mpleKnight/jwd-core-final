package com.epam.jwd.core_final.domain;

import java.util.Map;

/**
 * crew {@link java.util.Map<Role, Short>}
 * flightDistance {@link Long} - total available flight distance
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class Spaceship extends AbstractBaseEntity {

    private final Long id;
    private final Map<Role, Short> crew;
    private Long flightDistance;
    private Boolean isReadyForNextMissions;
    private final String name;

    public Spaceship(Map<Role, Short> crew, Long flightDistance, String name){
        this.id = this.getId();
        this.crew = crew;
        this.flightDistance = flightDistance;
        this.isReadyForNextMissions = true;
        this.name = name;
    }

    @Override
    public Long getId(){
        return id;
    }

    @Override
    public String getName(){
        return name;
    }

    public void setFlightDistance(Long flightDistance){
        this.flightDistance = flightDistance;
    }

    public void setReadyForNextMissions(Boolean readyForNextMissions){
        isReadyForNextMissions = readyForNextMissions;
    }

    public Map<Role, Short> getCrew(){
        return crew;
    }

    public Long getFlightDistance(){
        return flightDistance;
    }

    public Boolean getReadyForNextMissions(){
        return isReadyForNextMissions;
    }
}
