package com.epam.jwd.core_final.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilesInfoManipulator{

    public static List<String> readFile(String path) throws IOException{
        return Files.lines(Path.of(path)).collect(Collectors.toList());
    }

    public static List<String> separateString(String str, String delimiter){
        return Arrays.asList(str.split(delimiter).clone());
    }
}
