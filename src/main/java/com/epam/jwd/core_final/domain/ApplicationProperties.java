package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.io.File;

/**
 * This class should be IMMUTABLE!
 * <p>
 * Expected fields:
 * <p>
 * inputRootDir {@link String} - base dir for all input files
 * outputRootDir {@link String} - base dir for all output files
 * crewFileName {@link String}
 * missionsFileName {@link String}
 * spaceshipsFileName {@link String}
 * <p>
 * fileRefreshRate {@link Integer}
 * dateTimeFormat {@link String} - date/time format for {@link java.time.format.DateTimeFormatter} pattern
 */
public class ApplicationProperties{

    private static final String separator = File.separator;
    private static ApplicationProperties applicationProperties;
    private final String inputRootDir;
    private final String outputRootDir;
    private final String crewFileName;
    private final String missionsFileName;
    private final String spaceshipsFileName;
    private final Integer fileRefreshRate;
    private final String dateTimeFormat;

    private ApplicationProperties(String inputRootDir, String outputRootDir, String crewFileName,
                                 String missionsFileName, String spaceshipsFileName, Integer fileRefreshRate,
                                 String dateTimeFormat){
        this.inputRootDir = inputRootDir;
        this.outputRootDir = outputRootDir;
        this.crewFileName = crewFileName;
        this.missionsFileName = missionsFileName;
        this.spaceshipsFileName = spaceshipsFileName;
        this.fileRefreshRate = fileRefreshRate;
        this.dateTimeFormat = dateTimeFormat;
    }

    public static void populateProperties(){
        applicationProperties = new ApplicationProperties(
                PropertyReaderUtil.getPROPERTIES().getProperty("inputRootDir"),
                PropertyReaderUtil.getPROPERTIES().getProperty("outputRootDir"),
                PropertyReaderUtil.getPROPERTIES().getProperty("crewFileName"),
                PropertyReaderUtil.getPROPERTIES().getProperty("missionsFileName"),
                PropertyReaderUtil.getPROPERTIES().getProperty("spaceshipsFileName"),
                Integer.parseInt(PropertyReaderUtil.getPROPERTIES().getProperty("fileRefreshRate")),
                PropertyReaderUtil.getPROPERTIES().getProperty("dateTimeFormat")
        );
    }

    public String getInputRootDir(){
        return inputRootDir;
    }

    public String getSpaceshipsFileName(){
        return "src" + separator + "main" + separator + "resources"
                + separator + inputRootDir + separator + spaceshipsFileName;
    }

    public String getOutputRootDir(){
        return outputRootDir;
    }

    public String getCrewFileName(){
        return "src" + separator + "main" + separator + "resources"
                + separator + inputRootDir + separator + crewFileName;
    }

    public String getMissionsFileName(){
        return "src" + separator + "main" + separator + "resources"
                + separator + inputRootDir + separator + missionsFileName;
    }

    public Integer getFileRefreshRate(){
        return fileRefreshRate;
    }

    public static ApplicationProperties getApplicationProperties(){
        return applicationProperties;
    }

    public String getDateTimeFormat(){
        return dateTimeFormat;
    }
}
