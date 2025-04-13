package com.arun.mongodb.microservice.order_service.client;

import com.arun.mongodb.microservice.order_service.util.OrderUtility;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = OrderUtility.NOTIFICATION_SERVICE)
public interface NotificationClient {

    @GetMapping(OrderUtility.NOTIFICATION_BASE_URL)
    void sendNotification(@RequestParam String consumerName);
}
