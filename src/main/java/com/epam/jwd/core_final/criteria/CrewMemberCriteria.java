package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

import java.util.Objects;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember>{

    private final Long id;
    private final String name;
    private final Role role;
    private final Rank rank;
    private final Boolean isReadyForNextMissions;
    private final Boolean notOnMission;

    private CrewMemberCriteria(Long id, String name, Role role, Rank rank,
                               Boolean onMission, Boolean isReadyForNextMissions){
        this.id = id;
        this.name = name;
        this.role = role;
        this.rank = rank;
        this.notOnMission = onMission;
        this.isReadyForNextMissions = isReadyForNextMissions;
    }

    public static CrewMemberCriteriaBuilder builder(){
        return new CrewMemberCriteriaBuilder();
    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Role getRole(){
        return role;
    }

    public Rank getRank(){
        return rank;
    }

    public Boolean getNotOnMission(){
        return notOnMission;
    }

    public Boolean getReadyForNextMissions(){
        return isReadyForNextMissions;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, name, role, rank, notOnMission, isReadyForNextMissions);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrewMemberCriteria that = (CrewMemberCriteria) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                role == that.role &&
                rank == that.rank &&
                Objects.equals(notOnMission, that.notOnMission) &&
                Objects.equals(isReadyForNextMissions, that.isReadyForNextMissions);
    }

    @Override
    public String toString(){
        return "CrewMemberCriteria{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", rank=" + rank +
                ", onMission=" + notOnMission +
                ", isReadyForNextMissions=" + isReadyForNextMissions +
                '}';
    }

    public static class CrewMemberCriteriaBuilder{

        private Long id;
        private String name;
        private Role role;
        private Rank rank;
        private Boolean notOnMission;
        private Boolean isReadyForNextMissions;

        public CrewMemberCriteriaBuilder id(Long id){
            this.id = id;
            return this;
        }

        public CrewMemberCriteriaBuilder onMission(Boolean onMission){
            this.notOnMission = onMission;
            return this;
        }

        public CrewMemberCriteriaBuilder name(String name){
            this.name = name;
            return this;
        }

        public CrewMemberCriteriaBuilder role(Role role){
            this.role = role;
            return this;
        }

        public CrewMemberCriteriaBuilder rank(Rank rank){
            this.rank = rank;
            return this;
        }

        public CrewMemberCriteriaBuilder readyForNextMissions(Boolean isReadyForNextMissions){
            this.isReadyForNextMissions = isReadyForNextMissions;
            return this;
        }

        public CrewMemberCriteria build(){
            return new CrewMemberCriteria(
                    this.id,
                    this.name,
                    this.role,
                    this.rank,
                    this.notOnMission,
                    this.isReadyForNextMissions
            );
        }
    }
}
