package com.arun.mongodb.microservice.notification_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notify")
@Slf4j
public class NotificationController {

    @GetMapping
    public void sendNotification(@RequestParam String consumerName) {
        log.info("Hi {}", consumerName+", your order has been created!");
        log.info("Thanks For Shopping !!");
    }
}
