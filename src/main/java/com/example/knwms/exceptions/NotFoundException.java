package com.example.knwms.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String className, long id) {
        super(String.format("Could not find %s with an ID of %s", className, id));
    }

    public NotFoundException(String className, String name) {
        super(String.format("Could not find %s with a name of %s", className, name));
    }
}