package com.flogramming.repository;

import com.flogramming.domain.ShopeeOrder;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class ShopeeOrderRepositoryWithBagRelationshipsImpl implements ShopeeOrderRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<ShopeeOrder> fetchBagRelationships(Optional<ShopeeOrder> shopeeOrder) {
        return shopeeOrder.map(this::fetchPayments);
    }

    @Override
    public Page<ShopeeOrder> fetchBagRelationships(Page<ShopeeOrder> shopeeOrders) {
        return new PageImpl<>(
            fetchBagRelationships(shopeeOrders.getContent()),
            shopeeOrders.getPageable(),
            shopeeOrders.getTotalElements()
        );
    }

    @Override
    public List<ShopeeOrder> fetchBagRelationships(List<ShopeeOrder> shopeeOrders) {
        return Optional.of(shopeeOrders).map(this::fetchPayments).orElse(Collections.emptyList());
    }

    ShopeeOrder fetchPayments(ShopeeOrder result) {
        return entityManager
            .createQuery(
                "select shopeeOrder from ShopeeOrder shopeeOrder left join fetch shopeeOrder.payments where shopeeOrder is :shopeeOrder",
                ShopeeOrder.class
            )
            .setParameter("shopeeOrder", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<ShopeeOrder> fetchPayments(List<ShopeeOrder> shopeeOrders) {
        return entityManager
            .createQuery(
                "select distinct shopeeOrder from ShopeeOrder shopeeOrder left join fetch shopeeOrder.payments where shopeeOrder in :shopeeOrders",
                ShopeeOrder.class
            )
            .setParameter("shopeeOrders", shopeeOrders)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }
}
