package com.epam.jwd.core_final.context.impl.menu.search;

public class SearchFlightMission{

    private static SearchFlightMission searchFlightMission;

    private SearchFlightMission(){
    }

    public static SearchFlightMission getSearchFlightMission(){
        if (searchFlightMission == null){
            searchFlightMission = new SearchFlightMission();
        }
        return searchFlightMission;
    }
}
