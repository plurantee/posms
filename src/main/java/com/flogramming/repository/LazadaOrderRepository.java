package com.flogramming.repository;

import com.flogramming.domain.LazadaOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the LazadaOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LazadaOrderRepository extends JpaRepository<LazadaOrder, Long> {
}
