package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.intf.Application;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.util.PropertyReaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args){
        try {
            PropertyReaderUtil.loadProperties();
            Application.start();
        } catch (InvalidStateException e){
            LOGGER.error(e.getMessage());
        }
    }
}