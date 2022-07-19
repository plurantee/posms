package com.flogramming.repository;

import com.flogramming.domain.Client;
import com.flogramming.domain.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the Inventory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientInventoryRepository extends InventoryRepository {
    Inventory findBySku(String sku);
    Page<Inventory> findByClient(Client client, Pageable pageable);
}
