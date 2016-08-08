package com.dreamers.config;

import com.dreamers.exception.NotFound;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

//TODO don't rly catch smth, mb cuz of DispatcherServlet
@ControllerAdvice(annotations = RestController.class)
public class ControllerConfiguration {

    @ExceptionHandler(value = NotFound.class)
    @ResponseBody
    private ResponseEntity<String> notFound(NotFound n) {

        return new ResponseEntity<>(n.toString(), HttpStatus.NOT_FOUND);
    }

/*    @ExceptionHandler(value = DataIntegrityViolationException.class)
    @ResponseBody
    private ResponseEntity<String> SQLError(DataIntegrityViolationException n) {

        return new ResponseEntity<>(n.getMessage(), HttpStatus.BAD_REQUEST);
    }*/


/*    @ExceptionHandler(ConstraintViolationException.class)
    private ResponseEntity<Set<String>> handleConstraintViolation(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

        Set<String> messages = new HashSet<>(constraintViolations.size());
        messages.addAll(constraintViolations.stream()
                .map(constraintViolation -> String.format("Field %s with value '%s' %s", constraintViolation.getPropertyPath(),
                        constraintViolation.getInvalidValue(), constraintViolation.getMessage()))
                .collect(Collectors.toList()));

        return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
    }*/
}