package com.flogramming.util;

import com.flogramming.domain.LazadaOrder;
import com.flogramming.domain.OrderTracker;
import com.flogramming.domain.ShopeeOrder;

public class OrderTrackerUtil {
    public static OrderTracker mapLazadaToOrderTracker(LazadaOrder lazadaOrder) {
        OrderTracker orderTracker = new OrderTracker();
        orderTracker.setSite("LAZADA");

        orderTracker.setId(lazadaOrder.getId());
        orderTracker.setOrderItemId(lazadaOrder.getOrderItemId());
        orderTracker.setSkuReference(lazadaOrder.getSellerSku());
        orderTracker.setStatus(lazadaOrder.getStatus());
        orderTracker.setOrderType(lazadaOrder.getOrderType());
        orderTracker.setCourier(lazadaOrder.getShippingProvider());
        return orderTracker;

    }

    public static OrderTracker mapShopeeToOrderTracker(ShopeeOrder shopeeOrder) {
        OrderTracker orderTracker = new OrderTracker();
        orderTracker.setSite("SHOPEE");

        orderTracker.setId(shopeeOrder.getId());
        orderTracker.setOrderItemId(shopeeOrder.getOrderId());
        orderTracker.setSkuReference(shopeeOrder.getSkuReferenceNo());
        orderTracker.setStatus(shopeeOrder.getOrderStatus());
        orderTracker.setCourier(shopeeOrder.getShippingOption());
        return orderTracker;

    }
}
