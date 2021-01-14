package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

public class JSONOutPut{

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(JSONOutPut.class);

    public static void output(BaseEntity entity){
        LOGGER.info("JSON mission's format");
        File file = new File(ApplicationProperties.getApplicationProperties().getJSONFileDir());
        if (file.exists()){
            appendInfo(entity, file);
        } else {
            createFile(entity, file);
        }
    }

    private static void appendInfo(BaseEntity entity, File file){
        try (Writer writer = new FileWriter(ApplicationProperties.getApplicationProperties().getJSONFileDir(), true)){
            writer.write(MAPPER.writeValueAsString(entity) + "\n");
        } catch (IOException e){
            LOGGER.error("JSON format appending error " + e.getMessage());
        }
    }

    private static void createFile(BaseEntity entity, File file){
        try{
            boolean result = file.createNewFile();
            if (!result){
                LOGGER.info("File have not been created");
            }
            Writer writer = new FileWriter(ApplicationProperties.getApplicationProperties().getJSONFileDir(), true);
            writer.write(MAPPER.writeValueAsString(entity) + "\n");
        } catch (JsonGenerationException | JsonMappingException e){
            LOGGER.error("Mistakes with JSON libs");
        } catch (IOException e){
            LOGGER.error("JSON format writing error " + e.getMessage());
        }
    }

    public static void init(){
        dirCheck();
        try (Writer writer = new FileWriter(ApplicationProperties.getApplicationProperties().getJSONFileDir(), false)){
            writer.write(new Date().toString());
        } catch (IOException e){
            LOGGER.error("JSON format writing error " + e.getMessage());
        }
    }

    private static void dirCheck(){
        File file = new File(ApplicationProperties.getApplicationProperties().getOutputRootDir());
        if (!file.exists()){
            file.mkdir();
        }
    }
}
