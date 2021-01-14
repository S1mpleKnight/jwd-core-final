package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

public class JSONOutPut{

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(JSONOutPut.class);

    public static void output(BaseEntity entity){
        LOGGER.info("JSON mission's format");
        LOGGER.info(ApplicationProperties.getApplicationProperties().getJSONFileDir());
        try (Writer writer = new FileWriter(ApplicationProperties.getApplicationProperties().getJSONFileDir(), true)){
            writer.write(MAPPER.writeValueAsString(entity) + "\n");
        } catch (IOException e){
            LOGGER.error("JSON format writing error " + e.getMessage());
        }
    }

    public static void init(){
        try (Writer writer = new FileWriter(ApplicationProperties.getApplicationProperties().getJSONFileDir(), false)){
            writer.write(new Date().toString());
        } catch (IOException e){
            LOGGER.error("JSON format writing error " + e.getMessage());
        }
    }
}
