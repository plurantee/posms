package com.flogramming.repository;

import com.flogramming.domain.ShopeeOrder;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ShopeeOrder entity.
 */
@Repository
public interface ShopeeOrderRepository extends ShopeeOrderRepositoryWithBagRelationships, JpaRepository<ShopeeOrder, Long> {
    default Optional<ShopeeOrder> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<ShopeeOrder> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<ShopeeOrder> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}
