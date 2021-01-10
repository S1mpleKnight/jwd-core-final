package com.epam.jwd.core_final.domain;

import java.util.Map;

/**
 * crew {@link java.util.Map<Role, Short>}
 * flightDistance {@link Long} - total available flight distance
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class Spaceship extends AbstractBaseEntity {

    private Map<Role, Short> crew;
    private Long flightDistance;
    private Boolean isReadyForNextMissions;
    private final String name;

    public Spaceship(Map<Role, Short> crew, Long flightDistance, String name){
        this.crew = crew;
        this.flightDistance = flightDistance;
        this.isReadyForNextMissions = true;
        this.name = name;
    }

    public void setCrew(Map<Role, Short> crew){
        this.crew = crew;
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
