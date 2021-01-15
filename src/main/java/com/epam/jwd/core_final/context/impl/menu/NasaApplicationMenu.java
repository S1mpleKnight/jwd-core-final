package com.epam.jwd.core_final.context.impl.menu;

import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.context.intf.ApplicationContext;
import com.epam.jwd.core_final.context.intf.ApplicationMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class NasaApplicationMenu implements ApplicationMenu{

    private static final Logger LOGGER = LoggerFactory.getLogger(NasaApplicationMenu.class);
    private static final Scanner SCANNER = new Scanner(System.in);
    private static NasaApplicationMenu menu;

    public NasaApplicationMenu(){
    }

    public static NasaApplicationMenu getMenu(){
        if (menu == null){
            menu = new NasaApplicationMenu();
        }
        return menu;
    }

    @Override
    public ApplicationContext getApplicationContext(){
        return NasaContext.getContext();
    }

    @Override
    public Object printAvailableOptions(){
        System.out.println("Options: ");
        System.out.println("h -> Help");
        System.out.println("i -> Insertion");
        System.out.println("s -> Search");
        System.out.println("u -> Update");
        System.out.println("e -> Exit");
        return 5;
    }

    @Override
    public Object handleUserInput(Object o){
        String str = (String) o;
        if (str.length() == 1 || str.matches("[A-Za-z]")){
            return true;
        } else {
            System.out.println("Wrong input. Try again.");
            return false;
        }
    }

    public void start(){
        LOGGER.info("Menu was opened");
        printAvailableOptions();
        String str;
        boolean flag;
        do{
            System.out.print("Enter the symbol: ");
            str = SCANNER.nextLine();
            flag = mainSwitch(str);
        }while (flag);
    }

    private Boolean mainSwitch(String str){
        String result = waitingRightSymbol(str);
        switch (result){
            case "h":
                printAvailableOptions();
                return true;
            case "i":

                return true;
            case "s":

                return true;
            case "u":
                
                return true;
            case "e":
                LOGGER.info("Menu closed");
                return false;
            default:
                System.out.println("Wrong symbol.");
                return true;
        }
    }

    private String waitingRightSymbol(String str){
        boolean end = false;
        do{
            if ((Boolean) handleUserInput(str)){
                end = true;
            } else {
                str = SCANNER.nextLine();
            }
        }while (!end);
        return str;
    }
}
