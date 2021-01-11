package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NasaApplicationMenu;
import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.util.function.Supplier;

public interface Application {

    static ApplicationMenu start() throws InvalidStateException {
        final ApplicationMenu menu = NasaApplicationMenu.getMenu();
        final Supplier<ApplicationContext> applicationContextSupplier = menu::getApplicationContext; // todo
        final NasaContext nasaContext = NasaContext.getContext();

        nasaContext.init();
        return applicationContextSupplier::get;
    }
}
