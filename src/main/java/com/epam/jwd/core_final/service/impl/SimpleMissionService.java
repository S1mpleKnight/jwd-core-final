package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.service.MissionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SimpleMissionService implements MissionService{

    private static SimpleMissionService missionService;

    private SimpleMissionService(){
    }

    public static SimpleMissionService getMissionService(){
        if (missionService == null){
            missionService = new SimpleMissionService();
        }
        return missionService;
    }

    @Override
    public List<FlightMission> findAllMissions(){
        return new ArrayList<>(NasaContext.getContext().retrieveBaseEntityList(FlightMission.class));
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria){
        FlightMissionCriteria missionCriteria = (FlightMissionCriteria) criteria;
        return NasaContext.getContext().retrieveBaseEntityList(FlightMission.class)
                .stream()
                .filter(mission -> missionCriteria.getName() == null
                        || mission.getName().equals(missionCriteria.getName()))
                .filter(mission -> missionCriteria.getSpaceship() == null
                        || mission.getSpaceship().getName().equals(missionCriteria.getSpaceship().getName()))
                .filter(mission -> missionCriteria.getDistance() == null
                        || mission.getDistance().equals(missionCriteria.getDistance()))
                .filter(mission -> missionCriteria.getMissionResult() == null
                        || mission.getMissionResult().equals(missionCriteria.getMissionResult()))
                .filter(mission -> missionCriteria.getId() == null
                        || mission.getId().equals(missionCriteria.getId()))
                .filter(mission -> missionCriteria.getEndDate() == null
                        || mission.getEndDate().equals(missionCriteria.getEndDate()))
                .filter(mission -> missionCriteria.getStartDate() == null
                        || mission.getStartDate().equals(missionCriteria.getStartDate()))
                .filter(mission -> missionCriteria.getCrew() == null
                        || mission.getCrew().containsAll(missionCriteria.getCrew()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria){
        return findAllMissionsByCriteria(criteria).stream().findFirst();
    }

    @Override
    public FlightMission updateSpaceshipDetails(FlightMission flightMission){
        return null;
    }

    @Override
    public FlightMission createMission(FlightMission flightMission){
        long count = NasaContext.getContext().retrieveBaseEntityList(FlightMission.class)
                .stream()
                .filter(mission -> mission.getName().equals(flightMission.getName()))
                .count();
        if (count != 0){
            return null;
        } else {
            return flightMission;
        }
    }
}
