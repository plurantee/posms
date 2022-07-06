package com.flogramming.repository;

import com.flogramming.domain.Client;
import com.flogramming.domain.Shop;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the Shop entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> findByClientCode(Client client);
}
