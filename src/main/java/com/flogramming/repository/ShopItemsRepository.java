package com.flogramming.repository;

import com.flogramming.domain.ShopItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ShopItems entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShopItemsRepository extends JpaRepository<ShopItems, Long> {
}
