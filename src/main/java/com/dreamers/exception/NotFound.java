package com.dreamers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such object")
public class NotFound extends RuntimeException {

    public NotFound(int id) {
        super("ID: " +id + " not found");
    }
    public NotFound(String value) {
        super("Object with value: " +value + " not found");
    }

    public String toString(){
        return this.getMessage() +"\n" + getClass();
    }
}