package com.epam.jwd.core_final.exception;

import java.util.Arrays;
import java.util.stream.Collectors;

public class UnknownEntityException extends RuntimeException{

    private final String entityName;
    private final Object[] args;

    public UnknownEntityException(String entityName){
        super();
        this.entityName = entityName;
        this.args = null;
    }

    public UnknownEntityException(String entityName, Object[] args){
        super();
        this.entityName = entityName;
        this.args = args;
    }

    @Override
    public String getMessage(){
        String message;
        if (args == null){
            message = "Exception happened with " + entityName;
        } else {
            String params = Arrays.stream(args)
                    .map(s -> (String) s)
                    .collect(Collectors.joining(", "));
            message = "Exception happened with " + entityName + " with args:\n" + params;
        }
        // todo
        // you should use entityName, args (if necessary)
        return message;
    }
}
