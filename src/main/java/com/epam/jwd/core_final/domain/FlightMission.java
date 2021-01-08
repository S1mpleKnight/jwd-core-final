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
public class FlightMission extends AbstractBaseEntity {
    private final Long id;
    private final String name;
    private final LocalDate startDate;
    private LocalDate endDate;
    private final Long distance;
    private Spaceship spaceship;
    private List<CrewMember> crew;
    private MissionResult missionResult;

    public FlightMission(String name, LocalDate startDate, Long distance,
                         Spaceship spaceship, List<CrewMember> crew){
        this.name = name;
        this.startDate = startDate;
        this.distance = distance;
        this.spaceship = spaceship;
        this.crew = crew;
        this.id = this.getId();
    }

    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
    }

    public void setSpaceship(Spaceship spaceship){
        this.spaceship = spaceship;
    }

    public void setCrew(List<CrewMember> crew){
        this.crew = crew;
    }

    public void setMissionResult(MissionResult missionResult){
        this.missionResult = missionResult;
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

    public List<CrewMember> getCrew(){
        return crew;
    }

    public MissionResult getMissionResult(){
        return missionResult;
    }

    @Override
    public String getName(){
        return name;
    }
}
