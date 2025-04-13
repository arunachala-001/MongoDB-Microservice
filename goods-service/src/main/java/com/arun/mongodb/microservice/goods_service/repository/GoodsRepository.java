package com.arun.mongodb.microservice.goods_service.repository;

import com.arun.mongodb.microservice.goods_service.model.Goods;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GoodsRepository extends MongoRepository<Goods, String> {

    Goods findByProductName(String productName);
}
