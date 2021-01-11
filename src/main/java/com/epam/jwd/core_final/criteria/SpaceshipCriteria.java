package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;

import java.util.Map;
import java.util.Objects;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {

    private Long id;
    private Map<Role, Short> crew;
    private Long flightDistance;
    private Boolean isReadyForNextMissions;
    private String name;

    private SpaceshipCriteria(Long id, Map<Role, Short> crew, Long flightDistance,
                              Boolean isReadyForNextMissions, String name){
        this.id = id;
        this.crew = crew;
        this.flightDistance = flightDistance;
        this.isReadyForNextMissions = isReadyForNextMissions;
        this.name = name;
    }

    @Override
    public String toString(){
        return "SpaceshipCriteria{" +
                "id=" + id +
                ", crew=" + crew +
                ", flightDistance=" + flightDistance +
                ", isReadyForNextMissions=" + isReadyForNextMissions +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpaceshipCriteria that = (SpaceshipCriteria) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(crew, that.crew) &&
                Objects.equals(flightDistance, that.flightDistance) &&
                Objects.equals(isReadyForNextMissions, that.isReadyForNextMissions) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, crew, flightDistance, isReadyForNextMissions, name);
    }

    public Long getId(){
        return id;
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

    public String getName(){
        return name;
    }

    public static SpaceshipCriteriaBuilder builder(){
        return new SpaceshipCriteriaBuilder();
    }

    public static class SpaceshipCriteriaBuilder{

        private Long id;
        private Map<Role, Short> crew;
        private Long flightDistance;
        private Boolean isReadyForNextMissions;
        private String name;

        public SpaceshipCriteriaBuilder id(Long id){
            this.id = id;
            return this;
        }

        public SpaceshipCriteriaBuilder crew(Map<Role, Short> crew){
            this.crew = crew;
            return this;
        }

        public SpaceshipCriteriaBuilder flightDistance(Long flightDistance){
            this.flightDistance = flightDistance;
            return this;
        }

        public SpaceshipCriteriaBuilder readyForNextMissions(Boolean isReadyForNextMissions){
            this.isReadyForNextMissions = isReadyForNextMissions;
            return this;
        }

        public SpaceshipCriteriaBuilder name(String name){
            this.name = name;
            return this;
        }

        public SpaceshipCriteria build(){
            return new SpaceshipCriteria(
                    this.id,
                    this.crew,
                    this.flightDistance,
                    this.isReadyForNextMissions,
                    this.name
            );
        }
    }
}
