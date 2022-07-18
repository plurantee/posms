package com.flogramming.repository;

import com.flogramming.domain.ShopeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ShopeeOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShopeeOrderRepository extends JpaRepository<ShopeeOrder, Long> {
}
