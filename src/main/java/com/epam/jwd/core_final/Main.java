package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.exception.InvalidStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static ApplicationMenu menu;

    public static void main(String[] args){
        try {
            menu = Application.start();
        } catch (InvalidStateException e){
            LOGGER.error(e.getMessage());
        }
    }
}