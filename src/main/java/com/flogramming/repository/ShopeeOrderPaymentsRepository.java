package com.flogramming.repository;

import com.flogramming.domain.ShopeeOrderPayments;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ShopeeOrderPayments entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShopeeOrderPaymentsRepository extends JpaRepository<ShopeeOrderPayments, Long> {}
