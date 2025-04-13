package com.arun.mongodb.microservice.order_service.repository;

import com.arun.mongodb.microservice.order_service.model.OrderTable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderTable, Long> {
}
