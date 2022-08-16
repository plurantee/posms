package com.flogramming.repository;

import com.flogramming.domain.Client;
import com.flogramming.domain.Shop;
import com.flogramming.domain.ShopeeOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the ShopeeOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientShopeeOrderRepository extends ShopeeOrderRepository, ShopeeOrderRepositoryWithBagRelationships {
    List<ShopeeOrder> findByShop(Shop shop);

    Page<ShopeeOrder> findByClient(Client client, Pageable pageable);

    List<ShopeeOrder> findByClient(Client client);


    List<ShopeeOrder> findByOrderId(String orderId);

    List<ShopeeOrder> findByTrackingNumber(String trackingNumber);

    long deleteByOrderId(String orderId);
}
