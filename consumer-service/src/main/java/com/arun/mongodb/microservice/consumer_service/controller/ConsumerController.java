package com.arun.mongodb.microservice.consumer_service.controller;

import com.arun.mongodb.microservice.consumer_service.dto.ConsumerDto;
import com.arun.mongodb.microservice.consumer_service.exception.ConsumerNotFoundException;
import com.arun.mongodb.microservice.consumer_service.model.Consumer;
import com.arun.mongodb.microservice.consumer_service.service.ConsumerService;
import com.arun.mongodb.microservice.consumer_service.util.ConsumerUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ConsumerUtility.BASE_URL)
public class ConsumerController {

    private final ConsumerService consumerService;

    @Autowired
    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @GetMapping("/name/{name}")
    public ConsumerDto fetchConsumerDtoByName(@PathVariable String name) throws ConsumerNotFoundException {
        return consumerService.findConsumerByName(name);
    }

    // Admin

    @PostMapping
    public String storeConsumerDetails(@RequestBody Consumer consumer) {
        return consumerService.saveConsumer(consumer);
    }

    @GetMapping("/{id}")
    public Consumer fetchConsumerById(@PathVariable String id) throws ConsumerNotFoundException {
        return consumerService.findConsumerById(id);
    }

    @GetMapping
    public Page<Consumer> fetchAllDetails(@RequestParam("offSet") int offSet, @RequestParam("pageNumber") int pageNumber) {
        return consumerService.findAllConsumer(offSet, pageNumber);
    }
}
