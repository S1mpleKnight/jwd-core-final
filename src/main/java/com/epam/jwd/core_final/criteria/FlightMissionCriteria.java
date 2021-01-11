package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Spaceship;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission>{

    private final Long id;
    private final String name;
    private final LocalDate startDate;
    private final Long distance;
    private final LocalDate endDate;
    private final Spaceship spaceship;
    private final List<CrewMember> crew;
    private final MissionResult missionResult;

    private FlightMissionCriteria(Long id, String name, LocalDate startDate,
                                  LocalDate endDate, Long distance, Spaceship spaceship,
                                  List<CrewMember> crew, MissionResult missionResult){
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.distance = distance;
        this.spaceship = spaceship;
        this.crew = crew;
        this.missionResult = missionResult;
    }

    public static FlightMissionCriteriaBuilder builder(){
        return new FlightMissionCriteriaBuilder();
    }

    public Long getId(){
        return id;
    }

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

    public List<CrewMember> getCrew(){
        return crew;
    }

    public MissionResult getMissionResult(){
        return missionResult;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, name, startDate, endDate, distance, spaceship, crew, missionResult);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightMissionCriteria that = (FlightMissionCriteria) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(distance, that.distance) &&
                Objects.equals(spaceship, that.spaceship) &&
                Objects.equals(crew, that.crew) &&
                missionResult == that.missionResult;
    }

    @Override
    public String toString(){
        return "FlightMissionCriteria{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", distance=" + distance +
                ", spaceship=" + spaceship +
                ", crew=" + crew +
                ", missionResult=" + missionResult +
                '}';
    }

    public static class FlightMissionCriteriaBuilder{

        private Long id;
        private String name;
        private LocalDate startDate;
        private LocalDate endDate;
        private Long distance;
        private Spaceship spaceship;
        private List<CrewMember> crew;
        private MissionResult missionResult;

        public FlightMissionCriteriaBuilder id(Long id){
            this.id = id;
            return this;
        }

        public FlightMissionCriteriaBuilder name(String name){
            this.name = name;
            return this;
        }

        public FlightMissionCriteriaBuilder startDate(LocalDate startDate){
            this.startDate = startDate;
            return this;
        }

        public FlightMissionCriteriaBuilder endDate(LocalDate endDate){
            this.endDate = endDate;
            return this;
        }

        public FlightMissionCriteriaBuilder distance(Long distance){
            this.distance = distance;
            return this;
        }

        public FlightMissionCriteriaBuilder spaceship(Spaceship spaceship){
            this.spaceship = spaceship;
            return this;
        }

        public FlightMissionCriteriaBuilder crew(List<CrewMember> crew){
            this.crew = crew;
            return this;
        }

        public FlightMissionCriteriaBuilder missionResult(MissionResult missionResult){
            this.missionResult = missionResult;
            return this;
        }

        public FlightMissionCriteria build(){
            return new FlightMissionCriteria(
                    this.id,
                    this.name,
                    this.startDate,
                    this.endDate,
                    this.distance,
                    this.spaceship,
                    this.crew,
                    this.missionResult
            );
        }
    }
}

