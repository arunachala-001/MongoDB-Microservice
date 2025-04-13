package com.arun.mongodb.microservice.goods_service.service;

import com.arun.mongodb.microservice.goods_service.model.Goods;
import com.arun.mongodb.microservice.goods_service.repository.GoodsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class GoodsServiceTest {

    @InjectMocks
    private GoodsService goodsService;

    @Mock
    private GoodsRepository goodsRepository;

    Goods goods = new Goods();

    @BeforeEach
    void init() {
        goods = Goods.builder()
                .goodsId("goods_id1")
                .productName("Ghee")
                .price(500.00)
                .stock(100)
                .isAvailable(true)
                .build();
    }

    @Test
    void test_savingGoods() {
        Mockito.when(goodsRepository.save(goods)).thenReturn(goods);
        Mockito.when(goodsRepository.findAll()).thenReturn(List.of(goods));
        goodsService.saveGoods(goods);
        Assertions.assertEquals(1, goodsRepository.findAll().size());

    }
}
