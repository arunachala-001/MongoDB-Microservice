package com.arun.mongodb.microservice.consumer_service.model;

import com.fasterxml.jackson.databind.annotation.EnumNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "consumer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Consumer {

    @Id
    private String consumerId;
    private String consumerName;
    private String emailAddress;
    private int age;
    private ProfileStatus status;
}
