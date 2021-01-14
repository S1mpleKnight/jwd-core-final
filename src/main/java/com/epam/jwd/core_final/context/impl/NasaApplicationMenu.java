package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.intf.ApplicationContext;
import com.epam.jwd.core_final.context.intf.ApplicationMenu;

public class NasaApplicationMenu implements ApplicationMenu{

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
        return null;
    }

    @Override
    public Object handleUserInput(Object o){
        return null;
    }
}
