package com.flogramming.repository;

import com.flogramming.domain.ShopeeOrder;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ShopeeOrderRepositoryWithBagRelationships {
    Optional<ShopeeOrder> fetchBagRelationships(Optional<ShopeeOrder> shopeeOrder);

    List<ShopeeOrder> fetchBagRelationships(List<ShopeeOrder> shopeeOrders);

    Page<ShopeeOrder> fetchBagRelationships(Page<ShopeeOrder> shopeeOrders);
}
