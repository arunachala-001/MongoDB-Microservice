package com.arun.mongodb.microservice.goods_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "goods")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Goods {

    @Id
    private String goodsId;
    private String productName;
    private int stock;
    private double price;
    private boolean isAvailable;

}
