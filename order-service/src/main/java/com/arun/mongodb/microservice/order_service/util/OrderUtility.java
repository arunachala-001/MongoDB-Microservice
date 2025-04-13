package com.arun.mongodb.microservice.order_service.util;

public class OrderUtility {

    public static final String BASE_URL = "/order-service";

    // Goods Client
    public static final String GOODS_SERVICE = "goods-service";

    public static final String GOODS_BASE_URL = "/goods-service";

    public static final String CREATE_GOODS_CLIENT = "/orders";


    // Consumer Client
    public static final String CONSUMER_SERVICE = "consumer-service";

    public static final String CONSUMER_BASE_URL = "/consumer-service";

    // Notification Client
    public static final String NOTIFICATION_SERVICE = "notification-service";

    public static final String NOTIFICATION_BASE_URL = "/notify";

    //Message
    public static final String PRODUCTS_NOT_FOUND = "Selected Products not available currently";
}
