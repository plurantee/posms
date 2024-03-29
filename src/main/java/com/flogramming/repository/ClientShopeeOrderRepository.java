package com.flogramming.repository;

import com.flogramming.domain.Client;
import com.flogramming.domain.LazadaOrder;
import com.flogramming.domain.Shop;
import com.flogramming.domain.ShopeeOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Spring Data SQL repository for the ShopeeOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientShopeeOrderRepository extends ShopeeOrderRepository, ShopeeOrderRepositoryWithBagRelationships {
    List<ShopeeOrder> findByShop(Shop shop);

    Page<ShopeeOrder> findByClientOrderByDateUploadedDesc(Client client, Pageable pageable);

    List<ShopeeOrder> findByClientOrderByDateUploadedDesc(Client client);


    List<ShopeeOrder> findByOrderIdOrderByDateUploadedDesc(String orderId);
    ShopeeOrder findByOrderIdAndSkuReferenceNoOrderByDateUploadedDesc(String orderId, String skuReferenceNo);

    List<ShopeeOrder> findByTrackingNumberOrderByDateUploadedDesc(String trackingNumber);

    long deleteByOrderId(String orderId);

    Set<ShopeeOrder> findByDateUploadedBetweenOrderByDateUploadedDesc(ZonedDateTime zStartDate, ZonedDateTime zEndDate);
    Set<ShopeeOrder> findByOrderStatusAndDateUploadedBetweenOrderByDateUploadedDesc(String orderStatus, ZonedDateTime startDate, ZonedDateTime endDate);
}
