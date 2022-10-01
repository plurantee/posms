package com.flogramming.repository;

import com.flogramming.domain.Client;
import com.flogramming.domain.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the Inventory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientInventoryRepository extends InventoryRepository {
    Page<Inventory> findByClientOrderByIdDesc(Client client, Pageable pageable);

    List<Inventory> findByClient(Client client);

    Inventory findBySkuAndClient(String sku, Client client);
}
