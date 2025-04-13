package com.arun.mongodb.microservice.consumer_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsumerDto {

    private String consumerId;
    private String consumerName;
    private String emailAddress;
}
