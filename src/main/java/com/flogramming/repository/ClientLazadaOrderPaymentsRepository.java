package com.flogramming.repository;

import com.flogramming.domain.LazadaOrderPayments;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the LazadaOrderPayments entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientLazadaOrderPaymentsRepository extends LazadaOrderPaymentsRepository {
    Page<LazadaOrderPayments> findByOrderItemNo(String orderItemNo, Pageable pageable);
}
