package com.arun.mongodb.microservice.order_service.service;

import com.arun.mongodb.microservice.order_service.client.ConsumerClient;
import com.arun.mongodb.microservice.order_service.client.GoodsClient;
import com.arun.mongodb.microservice.order_service.client.NotificationClient;
import com.arun.mongodb.microservice.order_service.dto.CustomerDto;
import com.arun.mongodb.microservice.order_service.dto.GoodsDto;
import com.arun.mongodb.microservice.order_service.dto.OrderStatus;
import com.arun.mongodb.microservice.order_service.exception.ProductsUnAvailableException;
import com.arun.mongodb.microservice.order_service.model.OrderTable;
import com.arun.mongodb.microservice.order_service.repository.OrderRepository;
import com.arun.mongodb.microservice.order_service.request.OrderRequest;
import com.arun.mongodb.microservice.order_service.util.OrderUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    OrderRepository orderRepository;

    GoodsClient goodsClient;

    ConsumerClient consumerClient;

    NotificationClient notificationClient;

    @Autowired
    public OrderService(OrderRepository orderRepository, GoodsClient goodsClient, ConsumerClient consumerClient, NotificationClient notificationClient) {
        this.orderRepository = orderRepository;
        this.goodsClient = goodsClient;
        this.consumerClient = consumerClient;
        this.notificationClient = notificationClient;
    }

    @Transactional
    public OrderTable createOrder(List<OrderRequest> request, String consumerName, int quantity) throws ProductsUnAvailableException {
        List<String> productName = request.stream().map(OrderRequest::getProductName).toList();
        List<GoodsDto> goods = goodsClient.placeOrder(productName, quantity);

        if(goods.isEmpty()) {
            throw new ProductsUnAvailableException(OrderUtility.PRODUCTS_NOT_FOUND);
        }

        CustomerDto customerDto = consumerClient.fetchConsumerDtoByName(consumerName);

        OrderTable orderTable = OrderTable.builder()
                .customer(customerDto)
                .goods(goods)
                .quantity(quantity)
                .totalPrice(goods.stream().mapToDouble(g -> g.getPrice()*quantity).sum())
                .orderStatus(OrderStatus.CREATED)
                .build();

        orderRepository.save(orderTable);

        notificationClient.sendNotification(consumerName);

        return orderTable;
    }

}
