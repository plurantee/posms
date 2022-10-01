package com.flogramming.repository;

import com.flogramming.domain.LazadaOrder;
import com.flogramming.domain.LazadaOrderPayments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Spring Data SQL repository for the LazadaOrderPayments entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientLazadaOrderPaymentsRepository extends LazadaOrderPaymentsRepository {
    Page<LazadaOrderPayments> findByOrderItemNo(String orderItemNo, Pageable pageable);

    List<LazadaOrderPayments> findByTransactionDateBetweenOrderByTransactionDateDesc(ZonedDateTime startDate, ZonedDateTime endDate);
}
