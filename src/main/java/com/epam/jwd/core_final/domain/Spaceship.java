package com.epam.jwd.core_final.domain;

import java.util.Map;
import java.util.Objects;

/**
 * crew {@link java.util.Map<Role, Short>}
 * flightDistance {@link Long} - total available flight distance
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class Spaceship extends AbstractBaseEntity{

    private final Long id;
    private final Map<Role, Short> crew;
    private final String name;
    private Long flightDistance;
    private Boolean isReadyForNextMissions;
    private Boolean notOnMission;

    public Spaceship(Map<Role, Short> crew, Long flightDistance, String name){
        this.notOnMission = true;
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

    @Override
    public int hashCode(){
        return Objects.hash(id, crew, flightDistance, isReadyForNextMissions, notOnMission, name);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spaceship spaceship = (Spaceship) o;
        return Objects.equals(id, spaceship.id) &&
                Objects.equals(crew, spaceship.crew) &&
                Objects.equals(flightDistance, spaceship.flightDistance) &&
                Objects.equals(isReadyForNextMissions, spaceship.isReadyForNextMissions) &&
                Objects.equals(notOnMission, spaceship.notOnMission) &&
                Objects.equals(name, spaceship.name);
    }

    public Boolean getNotOnMission(){
        return notOnMission;
    }

    public void setNotOnMission(Boolean notOnMission){
        this.notOnMission = notOnMission;
    }

    public Map<Role, Short> getCrew(){
        return crew;
    }

    public Long getFlightDistance(){
        return flightDistance;
    }

    public void setFlightDistance(Long flightDistance){
        this.flightDistance = flightDistance;
    }

    public Boolean getReadyForNextMissions(){
        return isReadyForNextMissions;
    }

    public void setReadyForNextMissions(Boolean readyForNextMissions){
        isReadyForNextMissions = readyForNextMissions;
    }
}
