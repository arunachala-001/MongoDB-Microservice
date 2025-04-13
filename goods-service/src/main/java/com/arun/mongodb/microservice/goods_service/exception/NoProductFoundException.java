package com.arun.mongodb.microservice.goods_service.exception;

public class NoProductFoundException extends Exception{

    public NoProductFoundException(String message) {
        super(message);
    }
}
