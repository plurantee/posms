package com.flogramming.web.rest;

import com.flogramming.domain.LazadaOrder;
import com.flogramming.domain.Order;
import com.flogramming.domain.OrderTracker;
import com.flogramming.domain.ShopeeOrder;
import com.flogramming.domain.enumeration.OrderCategory;
import com.flogramming.repository.ClientLazadaOrderRepository;
import com.flogramming.repository.ClientShopeeOrderRepository;
import liquibase.pro.packaged.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/order-tracker")
@Transactional
public class OrderTrackerResource {

    @Autowired
    private ClientLazadaOrderRepository clientLazadaOrderRepository;

    @Autowired
    private ClientShopeeOrderRepository clientShopeeOrderRepository;

    @GetMapping("{queryId}")
    public ResponseEntity<List<OrderTracker>> getOrder(@PathVariable String queryId) {
        List<OrderTracker> result = new ArrayList<>();
        List<ShopeeOrder> shopeeOrders = clientShopeeOrderRepository.findByOrderId(queryId);
        if (shopeeOrders.isEmpty()) {
            shopeeOrders = clientShopeeOrderRepository.findByTrackingNumber(queryId);
        }

        if (!shopeeOrders.isEmpty()) {
            shopeeOrders.forEach(order -> {
                result.add(mapShopeeToOrderTracker(order));
            });
            return ResponseEntity.ok(result);
        }
        List<LazadaOrder> lazadaOrders = clientLazadaOrderRepository.findByOrderItemId(queryId);
        if (lazadaOrders.isEmpty()) {
            lazadaOrders = clientLazadaOrderRepository.findByTrackingCode(queryId);
        }

        if (!lazadaOrders.isEmpty()) {
            lazadaOrders.forEach(order -> {
                result.add(mapLazadaToOrderTracker(order));
            });
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.ok(result);
    }

    public OrderTracker mapLazadaToOrderTracker(LazadaOrder lazadaOrder) {
        OrderTracker orderTracker = new OrderTracker();
        orderTracker.setSite("LAZADA");

        orderTracker.setId(lazadaOrder.getId());
        orderTracker.setOrderItemId(lazadaOrder.getOrderItemId());
        orderTracker.setSkuReference(lazadaOrder.getSellerSku());
        orderTracker.setStatus(lazadaOrder.getStatus());
        orderTracker.setOrderType(lazadaOrder.getOrderType());
        return orderTracker;

    }

    public OrderTracker mapShopeeToOrderTracker(ShopeeOrder shopeeOrder) {
        OrderTracker orderTracker = new OrderTracker();
        orderTracker.setSite("SHOPEE");

        orderTracker.setId(shopeeOrder.getId());
        orderTracker.setOrderItemId(shopeeOrder.getOrderId());
        orderTracker.setSkuReference(shopeeOrder.getSkuReferenceNo());
        orderTracker.setStatus(shopeeOrder.getOrderStatus());
        return orderTracker;

    }
}
