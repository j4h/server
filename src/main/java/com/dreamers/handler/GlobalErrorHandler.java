package com.dreamers.handler;

import com.dreamers.exception.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(value = NotFound.class)
    @ResponseBody
    private ResponseEntity<String> notFound(NotFound n) {

        return new ResponseEntity<>(n.toString(),HttpStatus.NOT_FOUND);
    }
}
