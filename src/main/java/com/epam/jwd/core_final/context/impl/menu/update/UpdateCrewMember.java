package com.epam.jwd.core_final.context.impl.menu.update;

import com.epam.jwd.core_final.context.impl.menu.search.SearchCrewMember;
import com.epam.jwd.core_final.domain.CrewMember;

import java.util.Optional;
import java.util.Scanner;

public class UpdateCrewMember{

    private static UpdateCrewMember updateCrewMember;
    private static final Scanner SCANNER = new Scanner(System.in);

    private UpdateCrewMember(){
    }

    public static UpdateCrewMember getUpdateCrewMember(){
        if (updateCrewMember == null){
            updateCrewMember = new UpdateCrewMember();
        }
        return updateCrewMember;
    }

    public void update(){
        Optional<CrewMember> member = SearchCrewMember.getSearchCrewMember().searchForUpdate();
        if (member.isEmpty()){
            System.out.println("There is nothing to update");
        } else {
            updateFields(member.get());
        }
    }

    private void updateFields(CrewMember member){
        if (includeField("not on mission")){
            takeNotOnMissionFromInput(member);
        }
        if (includeField("is alive")){
            takeIsAlive(member);
        }
    }

    private void takeNotOnMissionFromInput(CrewMember member){
        System.out.print("Enter is on mission(true/false): ");
        String str = SCANNER.nextLine();
        if ((str.matches("true") || str.matches("false"))){
            member.setNotOnMission(Boolean.parseBoolean(str));
        }
    }

    private void takeIsAlive(CrewMember member){
        System.out.print("Enter is on mission(true/false): ");
        String str = SCANNER.nextLine();
        if ((str.matches("true") || str.matches("false"))){
            member.setReadyForNextMissions(Boolean.parseBoolean(str));
        }
    }

    private boolean includeField(String str){
        System.out.print("Update field " + str + "(y/n): ");
        String answer;
        boolean statement = true;
        do{
            answer = SCANNER.nextLine();
            if (answer.equals("y") || answer.equals("n")){
                statement = false;
            } else {
                System.out.print("Try again: ");
                answer = SCANNER.nextLine();
            }
        }while (statement);
        return answer.equals("y");
    }
}
