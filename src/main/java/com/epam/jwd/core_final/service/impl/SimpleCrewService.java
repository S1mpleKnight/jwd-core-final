package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.service.CrewService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SimpleCrewService implements CrewService{

    private static SimpleCrewService crewService;

    private SimpleCrewService(){
    }

    public static SimpleCrewService getCrewService(){
        if (crewService == null){
            crewService = new SimpleCrewService();
        }
        return crewService;
    }

    @Override
    public List<CrewMember> findAllCrewMembers(){
        return new ArrayList<>(NasaContext.getContext().retrieveBaseEntityList(CrewMember.class));
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria){
        CrewMemberCriteria memberCriteria = (CrewMemberCriteria) criteria;
        return NasaContext.getContext().retrieveBaseEntityList(CrewMember.class)
                .stream()
                .filter(member -> memberCriteria.getNotOnMission() == null
                        || member.getNotOnMission().equals(memberCriteria.getNotOnMission()))
                .filter(member -> memberCriteria.getId() == null
                        || member.getId().equals(memberCriteria.getId()))
                .filter(member -> memberCriteria.getName() == null
                        || member.getName().equals(memberCriteria.getName()))
                .filter(member -> memberCriteria.getRank() == null
                        || member.getRank().equals(memberCriteria.getRank()))
                .filter(member -> memberCriteria.getReadyForNextMissions() == null
                        || member.getReadyForNextMissions().equals(memberCriteria.getReadyForNextMissions()))
                .filter(member -> memberCriteria.getRole() == null
                        || member.getRole().equals(memberCriteria.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria){
        return findAllCrewMembersByCriteria(criteria).stream().findFirst();
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember){
        return null;
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws RuntimeException{
        crewMember.setNotOnMission(false);
    }

    @Override
    public CrewMember createCrewMember(CrewMember crewMember) throws RuntimeException{
        long count = NasaContext.getContext().retrieveBaseEntityList(CrewMember.class)
                .stream()
                .filter(member -> member.getName().equals(crewMember.getName()))
                .count();
        if (count != 0){
            return null;
        } else {
            return crewMember;
        }
    }
}
