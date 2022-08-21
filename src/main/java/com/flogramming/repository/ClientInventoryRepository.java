package com.flogramming.repository;

import com.flogramming.domain.Client;
import com.flogramming.domain.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Inventory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientInventoryRepository extends InventoryRepository {
    Page<Inventory> findByClient(Client client, Pageable pageable);

    Inventory findBySkuAndClient(String sku, Client client);
}
