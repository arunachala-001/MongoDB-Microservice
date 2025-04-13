package com.arun.mongodb.microservice.consumer_service.exception;

public class ConsumerNotFoundException extends Exception{

    public ConsumerNotFoundException(String message) {
        super(message);
    }
}
