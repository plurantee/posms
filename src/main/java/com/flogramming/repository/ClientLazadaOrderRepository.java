package com.flogramming.repository;

import com.flogramming.domain.Client;
import com.flogramming.domain.LazadaOrder;
import com.flogramming.domain.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the LazadaOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientLazadaOrderRepository extends LazadaOrderRepository {
    List<LazadaOrder> findByShop(Shop shop);

    Page<LazadaOrder> findByClient(Client client, Pageable pageable);

    List<LazadaOrder> findByOrderItemId(String orderItemId);

    List<LazadaOrder> findByOrderNumber(String orderNumber);

    long deleteByOrderItemId(String orderItemId);

    List<LazadaOrder> findByTrackingCode(String trackingCode);
}
