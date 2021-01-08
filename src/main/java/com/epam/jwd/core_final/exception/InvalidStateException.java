package com.epam.jwd.core_final.exception;

import java.util.Arrays;
import java.util.stream.Collectors;

public class InvalidStateException extends Exception {
    private final String state;
    private final Object[] args;

    public InvalidStateException(String state) {
        super();
        this.state = state;
        this.args = null;
    }

    public InvalidStateException(String state, Object[] args) {
        super();
        this.state = state;
        this.args = args;
    }

    @Override
    public String getMessage() {
        String message;
        if (args == null){
            message = "Exception happened with " + state;
        } else {
            String params = Arrays.stream(args)
                    .map(s -> (String) s)
                    .collect(Collectors.joining(", "));
            message = "Exception happened with " + state + " with args:\n" + params;
        }
        // todo
        // you should use entityName, args (if necessary)
        return message;
    }
}
