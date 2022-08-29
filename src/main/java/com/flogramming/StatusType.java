package com.flogramming;

public enum StatusType {

    PAID("paid"),
    PENDING_PAYMENT("pending_payment"),
    RETURNED("returned"),
    PENDING_PICKUP("pending_pickup");
    private final String value;
    StatusType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
