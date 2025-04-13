package com.arun.mongodb.microservice.order_service.client;

import com.arun.mongodb.microservice.order_service.dto.GoodsDto;
import com.arun.mongodb.microservice.order_service.util.OrderUtility;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name =OrderUtility.GOODS_SERVICE)
public interface GoodsClient {

    @GetMapping(OrderUtility.GOODS_BASE_URL+OrderUtility.CREATE_GOODS_CLIENT)
    List<GoodsDto> placeOrder(@RequestParam List<String> productName, @RequestParam int quantity);
}
