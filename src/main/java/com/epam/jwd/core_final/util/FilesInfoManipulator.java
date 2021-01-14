package com.epam.jwd.core_final.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilesInfoManipulator{

    private static final Logger LOGGER = LoggerFactory.getLogger(FilesInfoManipulator.class);

    public static List<String> readFile(String path) throws IOException{
        LOGGER.info("Read file:" + path);
        return Files.lines(Path.of(path)).collect(Collectors.toList());
    }

    public static List<String> separateString(String str, String delimiter){
        return Arrays.asList(str.split(delimiter).clone());
    }
}
