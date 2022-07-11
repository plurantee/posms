package com.flogramming.repository;

import com.flogramming.domain.Shop;
import com.flogramming.domain.ShopeeOrder;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ShopeeOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientShopeeOrderRepository extends ShopeeOrderRepository {
    List<ShopeeOrder> findByShop(Shop shop);

    Optional<ShopeeOrder> findByOrderId(String orderId);
}
