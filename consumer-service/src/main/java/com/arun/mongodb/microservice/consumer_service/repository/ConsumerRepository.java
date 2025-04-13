package com.arun.mongodb.microservice.consumer_service.repository;

import com.arun.mongodb.microservice.consumer_service.model.Consumer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConsumerRepository extends MongoRepository<Consumer, String> {

    Consumer findByConsumerName(String consumerName);
}
