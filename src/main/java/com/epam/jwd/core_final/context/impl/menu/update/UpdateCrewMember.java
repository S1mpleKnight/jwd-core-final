package com.epam.jwd.core_final.context.impl.menu.update;

public class UpdateCrewMember{

    private static UpdateCrewMember updateCrewMember;

    private UpdateCrewMember(){
    }

    public static UpdateCrewMember getUpdateCrewMember(){
        if (updateCrewMember == null){
            updateCrewMember = new UpdateCrewMember();
        }
        return updateCrewMember;
    }

    
}
