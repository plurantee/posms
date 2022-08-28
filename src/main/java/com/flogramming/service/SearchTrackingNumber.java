package com.flogramming.service;

public class SearchTrackingNumber {
    private String trackingNumber;
    private boolean isLazada;

    public SearchTrackingNumber(String trackingNumber, boolean isLazada) {
        this.trackingNumber = trackingNumber;
        this.isLazada = isLazada;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public boolean isLazada() {
        return isLazada;
    }

    public void setLazada(boolean lazada) {
        isLazada = lazada;
    }
}
