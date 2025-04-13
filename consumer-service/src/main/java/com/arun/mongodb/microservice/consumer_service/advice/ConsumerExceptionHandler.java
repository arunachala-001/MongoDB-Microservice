package com.arun.mongodb.microservice.consumer_service.advice;

import com.arun.mongodb.microservice.consumer_service.exception.ConsumerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ConsumerExceptionHandler {

    @ExceptionHandler(ConsumerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String consumerNotFoundException(ConsumerNotFoundException ex) {
        return ex.getMessage();
    }
}
