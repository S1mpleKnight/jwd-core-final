package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.exception.InvalidStateException;

import java.time.LocalDate;

public enum MissionResult{
    CANCELLED,
    FAILED,
    PLANNED,
    IN_PROGRESS,
    COMPLETED;

    public static void assignMissionResult(FlightMission mission) throws InvalidStateException{
        if (mission.getEndDate().isAfter(LocalDate.now())
                || mission.getStartDate().isBefore(LocalDate.now())){
            mission.setMissionResult(MissionResult.IN_PROGRESS);
        } else if (mission.getStartDate().isAfter(LocalDate.now())){
            mission.setMissionResult(MissionResult.PLANNED);
        } else {
            int factor = (int) Math.floor(Math.random() * 3);
            mission.setMissionResult(missionResultChanges(factor, mission));
        }
    }

    private static MissionResult missionResultChanges(int factor, FlightMission mission) throws InvalidStateException{
        switch (factor){
            case 0:
                mission.getSpaceship().setNotOnMission(true);
                for (CrewMember member : mission.getCrew()){
                    member.setNotOnMission(true);
                }
                return MissionResult.CANCELLED;
            case 1:
                mission.getSpaceship().setNotOnMission(true);
                mission.getSpaceship().setFlightDistance(mission.getSpaceship().getFlightDistance()
                        + mission.getDistance());
                for (CrewMember member : mission.getCrew()){
                    member.setNotOnMission(true);
                }
                return MissionResult.COMPLETED;
            case 2:
                mission.getSpaceship().setNotOnMission(true);
                mission.getSpaceship().setReadyForNextMissions(false);
                for (CrewMember member : mission.getCrew()){
                    member.setNotOnMission(true);
                    member.setReadyForNextMissions(false);
                }
                return MissionResult.FAILED;
            default:
                throw new InvalidStateException("Wrong argument in switch");
        }
    }
}
