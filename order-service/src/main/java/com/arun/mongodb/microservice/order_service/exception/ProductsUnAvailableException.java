package com.arun.mongodb.microservice.order_service.exception;

public class ProductsUnAvailableException extends Exception{

    public ProductsUnAvailableException(String message) {
        super(message);
    }
}
