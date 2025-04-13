package com.arun.mongodb.microservice.goods_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodsDto {

    private String goodsId;
    private String productName;
    private double price;
}
