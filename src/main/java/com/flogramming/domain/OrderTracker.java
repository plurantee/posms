package com.flogramming.domain;

import java.time.ZonedDateTime;

public class OrderTracker {
    private long id;
    private String orderItemId;
    private String orderType;
    private String skuReference;
    private String status;
    private String site;
    private String barcodeNumber;

    private String courier;

    private ZonedDateTime dateUploaded;

    private ZonedDateTime dateReleasedOrCancelled;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getSkuReference() {
        return skuReference;
    }

    public void setSkuReference(String skuReference) {
        this.skuReference = skuReference;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getBarcodeNumber() {
        return barcodeNumber;
    }

    public void setBarcodeNumber(String barcodeNumber) {
        this.barcodeNumber = barcodeNumber;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public ZonedDateTime getDateUploaded() {
        return dateUploaded;
    }

    public void setDateUploaded(ZonedDateTime dateUploaded) {
        this.dateUploaded = dateUploaded;
    }

    public ZonedDateTime getDateReleasedOrCancelled() {
        return dateReleasedOrCancelled;
    }

    public void setDateReleasedOrCancelled(ZonedDateTime dateReleasedOrCancelled) {
        this.dateReleasedOrCancelled = dateReleasedOrCancelled;
    }
}
