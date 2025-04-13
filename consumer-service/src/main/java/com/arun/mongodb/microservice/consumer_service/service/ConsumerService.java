package com.arun.mongodb.microservice.consumer_service.service;

import com.arun.mongodb.microservice.consumer_service.dto.ConsumerDto;
import com.arun.mongodb.microservice.consumer_service.exception.ConsumerNotFoundException;
import com.arun.mongodb.microservice.consumer_service.model.Consumer;
import com.arun.mongodb.microservice.consumer_service.repository.ConsumerRepository;
import com.arun.mongodb.microservice.consumer_service.util.ConsumerUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

    private final ConsumerRepository consumerRepository;

    @Autowired
    public ConsumerService(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    public String saveConsumer(Consumer consumer) {
        if(consumer.getConsumerName() != null) {
            log.info("Adding {} ", consumer.getConsumerName());
            consumerRepository.save(consumer);
            return "Record Saved successfully!";
        }
        else {
            throw new NullPointerException("Form Cannot be empty!");
        }
    }

    public Consumer findConsumerById(String id) throws ConsumerNotFoundException {
        return consumerRepository.findById(id).orElseThrow(
                () -> new ConsumerNotFoundException(ConsumerUtility.CONSUMER_NOT_FOUND + id));
    }

    public ConsumerDto findConsumerByName(String name) throws ConsumerNotFoundException {
        Consumer consumer = consumerRepository.findByConsumerName(name);
        if(consumer == null) {
            throw new ConsumerNotFoundException(ConsumerUtility.CONSUMER_NOT_FOUND + name);
        }
        return ConsumerDto.builder()
                .consumerId(consumer.getConsumerId())
                .consumerName(consumer.getConsumerName())
                .emailAddress(consumer.getEmailAddress())
                .build();
    }

    //For Admin Use
    public Page<Consumer> findAllConsumer(int offSet, int pageSize) {
        return consumerRepository.findAll(PageRequest.of(offSet, pageSize));
    }
}
