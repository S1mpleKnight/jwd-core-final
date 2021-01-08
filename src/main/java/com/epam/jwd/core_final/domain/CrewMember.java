package com.epam.jwd.core_final.domain;

/**
 * Expected fields:
 * <p>
 * role {@link Role} - member role
 * rank {@link Rank} - member rank
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class CrewMember extends AbstractBaseEntity {
    private final Long id;
    private final String name;
    private final Role role;
    private final Rank rank;
    private Boolean isReadyForNextMissions;

    public Role getRole(){
        return role;
    }

    public Rank getRank(){
        return rank;
    }

    public Boolean getReadyForNextMissions(){
        return isReadyForNextMissions;
    }

    public void setReadyForNextMissions(Boolean readyForNextMissions){
        isReadyForNextMissions = readyForNextMissions;
    }

    public CrewMember(String name, Role role, Rank rank){
        this.name = name;
        this.role = role;
        this.rank = rank;
        this.id = this.getId();
        this.isReadyForNextMissions = true;
    }
}
