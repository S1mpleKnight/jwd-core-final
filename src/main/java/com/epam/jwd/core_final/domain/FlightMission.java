package com.epam.jwd.core_final.domain;

import java.time.LocalDate;
import java.util.List;

/**
 * Expected fields:
 * <p>
 * missions name {@link String}
 * start date {@link java.time.LocalDate}
 * end date {@link java.time.LocalDate}
 * distance {@link Long} - missions distance
 * assignedSpaceShift {@link Spaceship} - not defined by default
 * assignedCrew {@link java.util.List<CrewMember>} - list of missions members based on ship capacity - not defined by default
 * missionResult {@link MissionResult}
 */
public class FlightMission extends AbstractBaseEntity{

    private final Long id;
    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Long distance;
    private Spaceship spaceship;
    private List<CrewMember> crew;
    private MissionResult missionResult;

    public FlightMission(String name, LocalDate startDate, LocalDate endDate,
                         Long distance, Long id){
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.distance = distance;
        this.id = id;
    }

    @Override
    public Long getId(){
        return id;
    }

    @Override
    public String getName(){
        return name;
    }

    public LocalDate getStartDate(){
        return startDate;
    }

    public LocalDate getEndDate(){
        return endDate;
    }

    public Long getDistance(){
        return distance;
    }

    public Spaceship getSpaceship(){
        return spaceship;
    }

    public void setSpaceship(Spaceship spaceship){
        this.spaceship = spaceship;
    }

    public List<CrewMember> getCrew(){
        return crew;
    }

    public void setCrew(List<CrewMember> crew){
        this.crew = crew;
    }

    public MissionResult getMissionResult(){
        return missionResult;
    }

    public void setMissionResult(MissionResult missionResult){
        this.missionResult = missionResult;
    }
}
