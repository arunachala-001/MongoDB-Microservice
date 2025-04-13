package com.arun.mongodb.microservice.goods_service.exception;

public class StockUnAvailableException extends Exception{

    public StockUnAvailableException(String message) {
        super(message);
    }
}
