package com.arun.mongodb.microservice.order_service.advice;

import com.arun.mongodb.microservice.order_service.exception.ProductsUnAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrdersExceptionHandler {

    @ExceptionHandler(ProductsUnAvailableException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String productUnavailableException(ProductsUnAvailableException ex) {
        return ex.getMessage();
    }
}
