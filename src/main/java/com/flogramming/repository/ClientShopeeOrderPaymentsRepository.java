package com.flogramming.repository;

import com.flogramming.domain.LazadaOrderPayments;
import com.flogramming.domain.ShopeeOrderPayments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Spring Data SQL repository for the ShopeeOrderPayments entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientShopeeOrderPaymentsRepository extends ShopeeOrderPaymentsRepository {
    Page<ShopeeOrderPayments> findByOrderId(String orderId, Pageable pageable);
    List<ShopeeOrderPayments> findByOrderId(String orderId);

    List<ShopeeOrderPayments> findByPayoutCompletedDateBetweenOrderByPayoutCompletedDateDesc(ZonedDateTime startDate, ZonedDateTime endDate);
}
