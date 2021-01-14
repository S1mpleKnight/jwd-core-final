package com.epam.jwd.core_final.domain;

/**
 * Expected fields:
 * <p>
 * role {@link Role} - member role
 * rank {@link Rank} - member rank
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class CrewMember extends AbstractBaseEntity{

    private final Long id;
    private final String name;
    private final Role role;
    private final Rank rank;
    private Boolean notOnMission;
    private Boolean isReadyForNextMissions;

    public CrewMember(String name, Role role, Rank rank, Long id){
        this.notOnMission = true;
        this.name = name;
        this.role = role;
        this.rank = rank;
        this.id = id;
        this.isReadyForNextMissions = true;
    }

    @Override
    public Long getId(){
        return id;
    }

    @Override
    public String getName(){
        return name;
    }

    public Boolean getNotOnMission(){
        return notOnMission;
    }

    public void setNotOnMission(Boolean notOnMission){
        this.notOnMission = notOnMission;
    }

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
}
