package com.arun.mongodb.microservice.goods_service.advice;

import com.arun.mongodb.microservice.goods_service.exception.NoProductFoundException;
import com.arun.mongodb.microservice.goods_service.exception.StockUnAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GoodsExceptionHandler {

    @ExceptionHandler(NoProductFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String noProductFoundException(NoProductFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(StockUnAvailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String stockUnAvailableException(StockUnAvailableException ex) {
        return ex.getMessage();
    }
}
