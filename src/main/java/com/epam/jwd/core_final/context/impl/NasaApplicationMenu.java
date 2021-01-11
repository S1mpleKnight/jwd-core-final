package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;

public class NasaApplicationMenu implements ApplicationMenu{

    private static NasaApplicationMenu menu;

    public NasaApplicationMenu(){
    }

    @Override
    public ApplicationContext getApplicationContext(){
        return NasaContext.getContext();
    }

    @Override
    public Object printAvailableOptions(){
        return null;
    }

    @Override
    public Object handleUserInput(Object o){
        return null;
    }

    public static NasaApplicationMenu getMenu(){
        if (menu == null){
            menu = new NasaApplicationMenu();
        }
        return menu;
    }
}
