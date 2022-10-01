package com.flogramming.repository;

import com.flogramming.domain.Client;
import com.flogramming.domain.LazadaOrder;
import com.flogramming.domain.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

/**
 * Spring Data SQL repository for the LazadaOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientLazadaOrderRepository extends LazadaOrderRepository {
    List<LazadaOrder> findByShop(Shop shop);

    Page<LazadaOrder> findByClientOrderByDateUploadedDesc(Client client, Pageable pageable);

    List<LazadaOrder> findByOrderItemIdOrderByDateUploadedDesc(String orderItemId);

    List<LazadaOrder> findByOrderNumberOrderByDateUploadedDesc(String orderNumber);

    long deleteByOrderItemId(String orderItemId);

    List<LazadaOrder> findByTrackingCodeOrderByDateUploadedDesc(String trackingCode);

    Set<LazadaOrder> findByDateUploadedBetweenOrderByDateUploadedDesc(ZonedDateTime startDate, ZonedDateTime endDate);

    Set<LazadaOrder> findByStatusAndDateUploadedBetweenOrderByDateUploadedDesc(String status, ZonedDateTime startDate, ZonedDateTime endDate);
}
