package com.flogramming.domain;

import com.flogramming.domain.enumeration.OrderCategory;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Order {
    protected OrderCategory orderCategory;

    public OrderCategory getOrderCategory() {
        return orderCategory;
    }

    public void setOrderCategory() {
        orderCategory = OrderCategory.OTHER;
    }
}
