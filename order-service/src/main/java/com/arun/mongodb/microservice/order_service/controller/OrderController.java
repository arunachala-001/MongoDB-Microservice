package com.arun.mongodb.microservice.order_service.controller;

import com.arun.mongodb.microservice.order_service.exception.ProductsUnAvailableException;
import com.arun.mongodb.microservice.order_service.model.OrderTable;
import com.arun.mongodb.microservice.order_service.request.OrderRequest;
import com.arun.mongodb.microservice.order_service.service.OrderService;
import com.arun.mongodb.microservice.order_service.util.OrderUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(OrderUtility.BASE_URL)
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{consumerName}/{quantity}")
    public OrderTable createOrder(
            @RequestBody List<OrderRequest> request,
            @PathVariable String consumerName, @PathVariable int quantity) throws ProductsUnAvailableException {

        return orderService.createOrder(request, consumerName, quantity);
    }

    @GetMapping("/getPort")
    public String apiGatewayTest() {
        return "application running on port 8090";
    }
}
