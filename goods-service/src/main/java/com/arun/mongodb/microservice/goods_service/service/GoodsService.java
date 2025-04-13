package com.arun.mongodb.microservice.goods_service.service;

import com.arun.mongodb.microservice.goods_service.dto.GoodsDto;
import com.arun.mongodb.microservice.goods_service.exception.NoProductFoundException;
import com.arun.mongodb.microservice.goods_service.exception.StockUnAvailableException;
import com.arun.mongodb.microservice.goods_service.model.Goods;
import com.arun.mongodb.microservice.goods_service.repository.GoodsRepository;
import com.arun.mongodb.microservice.goods_service.util.GoodServiceUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsService {

    private final GoodsRepository goodsRepository;

    @Autowired
    public GoodsService(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    public ResponseEntity<String> saveGoods(Goods goods) {
        if(goods.getProductName() != null) {
            goodsRepository.save(goods);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(GoodServiceUtility.SAVE_PRODUCT + goods.getProductName());
        }

        throw new NullPointerException(GoodServiceUtility.EXCEPTION_SAVE);
    }

    public Goods findByGoodsName(String productName) throws NoProductFoundException {
        Goods product = goodsRepository.findByProductName(productName);

        if(product != null) {
            return product;
        }
        else {
            throw new NoProductFoundException(GoodServiceUtility.PRODUCT_NOT_FOUND_EXCEPTION+ productName);
        }
    }

    public Page<Goods> findAllGoods(int offSet, int pageNumber) {
        return goodsRepository.findAll(PageRequest.of(offSet,pageNumber));
    }

    public String updateGoods(String id, Goods goods) throws NoProductFoundException {
        Goods existingProduct = goodsRepository.findById(id).orElse(null);

        if (existingProduct != null) {
            existingProduct.setGoodsId(goods.getGoodsId());
            existingProduct.setProductName(goods.getProductName());
            existingProduct.setStock(existingProduct.getStock()+ goods.getStock());
            existingProduct.setAvailable(goods.isAvailable());

            goodsRepository.save(existingProduct);

            return "Record Updated";
        }

        else{
            throw new NoProductFoundException(GoodServiceUtility.PRODUCT_NOT_FOUND_EXCEPTION+ goods.getProductName());
        }


    }

    public List<GoodsDto> createOrder(List<String> productName, int quantity) {
        return productName.stream()
                .map(g -> {
                    try {
                        return findByGoodsName(g);
                    } catch (NoProductFoundException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                })
                .filter(g -> {
                    try {
                        return updateStockAvailability(g, quantity);
                    } catch (StockUnAvailableException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                })
                .map(this::mapToGoodsDto)
                .toList();

    }

    private GoodsDto mapToGoodsDto(Goods g) {
        return GoodsDto.builder()
                .goodsId(g.getGoodsId())
                .productName(g.getProductName())
                .price(g.getPrice())
                .build();
    }

    private boolean updateStockAvailability(Goods g, int qty) throws StockUnAvailableException {
        if(g.getStock() > qty) {
            g.setStock(g.getStock()-qty);
            goodsRepository.save(g);
            return true;
        }
        else {
            throw new StockUnAvailableException(GoodServiceUtility.STOCK_UNAVAILABLE_EXCEPTION);
        }
    }


}
