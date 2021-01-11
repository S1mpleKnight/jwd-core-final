package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyReaderUtil{

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyReaderUtil.class);
    private static final Properties PROPERTIES = new Properties();

    private PropertyReaderUtil(){
    }

    public static Properties getPROPERTIES(){
        return PROPERTIES;
    }

    /**
     * try-with-resource using FileInputStream
     *
     * @see {https://www.netjstech.com/2017/09/how-to-read-properties-file-in-java.html for an example}
     * <p>
     * as a result - you should populate {@link ApplicationProperties} with corresponding
     * values from property file
     */
    public static void loadProperties(){
        final String propertiesFileName = "src/main/resources/application.properties";
        try (InputStream io = new FileInputStream(propertiesFileName)){
            PROPERTIES.load(io);
            ApplicationProperties.populateProperties();
        } catch (FileNotFoundException e){
            LOGGER.error("Property file wasn't found");
        } catch (IOException e){
            LOGGER.error("Load property exception");
        }
    }
}
