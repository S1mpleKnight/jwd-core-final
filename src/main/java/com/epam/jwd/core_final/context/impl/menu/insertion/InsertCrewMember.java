package com.epam.jwd.core_final.context.impl.menu.insertion;

import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.service.impl.SimpleCrewService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InsertCrewMember{

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final SimpleCrewService CREW_SERVICE = SimpleCrewService.getCrewService();
    private static InsertCrewMember insertCrewMember;

    private InsertCrewMember(){
    }

    public static InsertCrewMember getInsertCrewMember(){
        if (insertCrewMember == null){
            insertCrewMember = new InsertCrewMember();
        }
        return insertCrewMember;
    }

    public void insert() throws InvalidStateException{
        List<String> list = takeArgs();
        Boolean check = checkInput(list);
        if (!check){
            throw new InvalidStateException("Invalid creating data");
        } else {
            CrewMember member = CrewMemberFactory.getFactory().create(list.toArray());
            member = CREW_SERVICE.createCrewMember(member);
            if (member == null){
                System.out.println("Person with this name is already exist");
            } else {
                NasaContext.getContext().addCrewMember(member);
            }
        }
    }

    private List<String> takeArgs(){
        List<String> args = new ArrayList<>();
        System.out.println("---------------------------------");
        System.out.println("Crew Member creation");
        System.out.print("Enter role number: ");
        args.add(SCANNER.nextLine());
        System.out.print("Enter name: ");
        args.add(SCANNER.nextLine());
        System.out.print("Enter rank number: ");
        args.add(SCANNER.nextLine());
        return args;
    }

    private Boolean checkInput(List<String> args){
        if (!args.get(1).matches("[A-Za-z ]+")){
            return false;
        } else if ((args.get(0).length() != 1) || (Integer.parseInt(args.get(0)) > 4)
                || Integer.parseInt(args.get(0)) < 1){
            return false;
        } else if ((args.get(2).length() != 1) || (Integer.parseInt(args.get(2)) > 4)
                || Integer.parseInt(args.get(2)) < 1){
            return false;
        } else {
            return true;
        }
    }
}
