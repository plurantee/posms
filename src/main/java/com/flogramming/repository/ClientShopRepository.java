package com.flogramming.repository;

import com.flogramming.domain.Client;
import com.flogramming.domain.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data SQL repository for the Shop entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientShopRepository extends ShopRepository {
    Page<Shop> findByClientCode(Client client, Pageable pageable);

    Optional<Shop> findByIdAndClientCode(Long id, Client client);
}
