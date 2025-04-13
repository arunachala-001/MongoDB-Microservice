package com.arun.mongodb.microservice.order_service.client;

import com.arun.mongodb.microservice.order_service.dto.CustomerDto;
import com.arun.mongodb.microservice.order_service.util.OrderUtility;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = OrderUtility.CONSUMER_SERVICE)
public interface ConsumerClient {

    @GetMapping(OrderUtility.CONSUMER_BASE_URL+"/name/{name}")
    CustomerDto fetchConsumerDtoByName(@PathVariable String name);
}
