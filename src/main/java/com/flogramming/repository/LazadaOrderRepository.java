package com.flogramming.repository;

import com.flogramming.domain.LazadaOrder;
import com.flogramming.domain.Shop;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the LazadaOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LazadaOrderRepository extends JpaRepository<LazadaOrder, Long> {
    List<LazadaOrder> findByShop(Shop shop);
}
