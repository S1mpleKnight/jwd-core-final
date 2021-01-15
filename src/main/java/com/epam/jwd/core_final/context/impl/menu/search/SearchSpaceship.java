package com.epam.jwd.core_final.context.impl.menu.search;

public class SearchSpaceship{

    private static SearchSpaceship searchSpaceship;

    private SearchSpaceship(){
    }

    public static SearchSpaceship getSearchSpaceship(){
        if (searchSpaceship == null){
            searchSpaceship = new SearchSpaceship();
        }
        return searchSpaceship;
    }
}
