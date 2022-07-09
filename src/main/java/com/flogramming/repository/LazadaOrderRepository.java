package com.flogramming.repository;

import com.flogramming.domain.LazadaOrder;
import com.flogramming.domain.Shop;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the LazadaOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LazadaOrderRepository extends JpaRepository<LazadaOrder, Long> {
    List<LazadaOrder> findByShop(Shop shop);

    Optional<LazadaOrder> findByOrderItemId(String orderItemId);
}
