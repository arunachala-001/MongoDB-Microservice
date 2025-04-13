package com.arun.mongodb.microservice.goods_service.controller;

import com.arun.mongodb.microservice.goods_service.dto.GoodsDto;
import com.arun.mongodb.microservice.goods_service.exception.NoProductFoundException;
import com.arun.mongodb.microservice.goods_service.model.Goods;
import com.arun.mongodb.microservice.goods_service.service.GoodsService;
import com.arun.mongodb.microservice.goods_service.util.GoodServiceUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(GoodServiceUtility.BASE_URL)
public class GoodsController {

    private final GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @PostMapping
    public ResponseEntity<String> storeGoods(@RequestBody Goods goods) {
        return goodsService.saveGoods(goods);
    }

    @PutMapping("{id}")
    public String updateGoodsByNewRec(@PathVariable String id, @RequestBody Goods goods) throws NoProductFoundException {
        return goodsService.updateGoods(id, goods);
    }

    @GetMapping("/{productName}")
    public Goods fetchById(@PathVariable String productName) throws NoProductFoundException {
        return goodsService.findByGoodsName(productName);
    }

    @GetMapping
    public Page<Goods> fetchAllGoods(@RequestParam("offSet") int offSet, @RequestParam("pageSize") int pageSize) {
        return goodsService.findAllGoods(offSet, pageSize);
    }

    @GetMapping(GoodServiceUtility.PLACE_ORDER)
    public List<GoodsDto> placeOrder(@RequestParam List<String> productName, @RequestParam int quantity) {
        return goodsService.createOrder(productName, quantity);
    }
}
