package com.epam.jwd.core_final.context.impl.menu.search;

import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.service.impl.SimpleCrewService;
import com.epam.jwd.core_final.util.JSONOutPut;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class SearchCrewMember{

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final SimpleCrewService CREW_SERVICE = SimpleCrewService.getCrewService();
    private static SearchCrewMember searchCrewMember;

    private SearchCrewMember(){
    }

    public static SearchCrewMember getSearchCrewMember(){
        if (searchCrewMember == null){
            searchCrewMember = new SearchCrewMember();
        }
        return searchCrewMember;
    }

    public void search(){
        System.out.println("---------------------------------");
        System.out.print("Search params: ");
        List<CrewMember> list = CREW_SERVICE.findAllCrewMembersByCriteria(takeCriteria());
        for (CrewMember member : list){
            JSONOutPut.output(member);
            System.out.println(member.getName() + " id: " + member.getId());
        }
    }

    public Optional<CrewMember> searchForUpdate(){
        return CREW_SERVICE.findCrewMemberByCriteria(takeCriteria());
    }

    private boolean includeField(String str){
        System.out.print("Include field " + str + "(y/n): ");
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

    private Criteria<CrewMember> takeCriteria(){
        CrewMemberCriteria.CrewMemberCriteriaBuilder builder = CrewMemberCriteria.builder();
        if (includeField("id")){
            takeIDFromInput(builder);
        }
        if (includeField("name")){
            takeNameFromInput(builder);
        }
        if (includeField("role")){
            takeRoleFromInput(builder);
        }
        if (includeField("rank")){
            takeRankFromInput(builder);
        }
        if (includeField("not on mission")){
            takeNotOnMissionFromInput(builder);
        }
        if (includeField("is alive")){
            takeIsAlive(builder);
        }
        return builder.build();
    }

    private void takeIDFromInput(CrewMemberCriteria.CrewMemberCriteriaBuilder builder){
        System.out.print("Enter id: ");
        String str = SCANNER.nextLine();
        if (!str.matches("[0-9]+")){
            builder.id(null);
        } else {
            builder.id(Long.parseLong(str));
        }
    }

    private void takeNameFromInput(CrewMemberCriteria.CrewMemberCriteriaBuilder builder){
        System.out.print("Enter name: ");
        String str = SCANNER.nextLine();
        if (!str.matches("[A-Za-z ]+")){
            builder.name(null);
        } else {
            builder.name(str);
        }
    }

    private void takeRoleFromInput(CrewMemberCriteria.CrewMemberCriteriaBuilder builder){
        System.out.print("Enter role: ");
        String str = SCANNER.nextLine();
        if (!(str.matches("[0-9]") || str.charAt(0) > '4' || str.charAt(0) < '1')){
            builder.role(null);
        } else {
            builder.role(Role.resolveRoleById(Long.parseLong(str)));
        }
    }

    private void takeRankFromInput(CrewMemberCriteria.CrewMemberCriteriaBuilder builder){
        System.out.print("Enter rank: ");
        String str = SCANNER.nextLine();
        if (!(str.matches("[0-9]") || str.charAt(0) > '4' || str.charAt(0) < '1')){
            builder.rank(null);
        } else {
            builder.rank(Rank.resolveRankById(Long.parseLong(str)));
        }
    }

    private void takeNotOnMissionFromInput(CrewMemberCriteria.CrewMemberCriteriaBuilder builder){
        System.out.print("Enter is on mission(true/false): ");
        String str = SCANNER.nextLine();
        if (!(str.matches("true") || str.matches("false"))){
            builder.onMission(null);
        } else {
            builder.onMission(Boolean.parseBoolean(str));
        }
    }

    private void takeIsAlive(CrewMemberCriteria.CrewMemberCriteriaBuilder builder){
        System.out.print("Enter is on mission(true/false): ");
        String str = SCANNER.nextLine();
        if (!(str.matches("true") || str.matches("false"))){
            builder.readyForNextMissions(null);
        } else {
            builder.readyForNextMissions(Boolean.parseBoolean(str));
        }
    }
}
