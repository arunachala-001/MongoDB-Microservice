package com.arun.mongodb.microservice.order_service.model;

import com.arun.mongodb.microservice.order_service.dto.CustomerDto;
import com.arun.mongodb.microservice.order_service.dto.GoodsDto;
import com.arun.mongodb.microservice.order_service.dto.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document(collection = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderTable {

    @Id
    private String orderId;
    private CustomerDto customer;
    private List<GoodsDto> goods;
    private int quantity;
    private double totalPrice;
    private OrderStatus orderStatus;

    @CreatedDate
    private Instant createdDate;

}
