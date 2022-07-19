package com.flogramming.domain;

import java.util.List;

public class UploadWaybillResponse {
    private byte[] file;
    private List<OrderTracker> orders;


    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public List<OrderTracker> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderTracker> orders) {
        this.orders = orders;
    }
}
