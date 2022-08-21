package com.flogramming.repository;

import com.flogramming.domain.LazadaOrderPayments;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the LazadaOrderPayments entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LazadaOrderPaymentsRepository extends JpaRepository<LazadaOrderPayments, Long> {}
