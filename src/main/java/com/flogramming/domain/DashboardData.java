package com.flogramming.domain;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class DashboardData {
    private Double profit;
    private List<Inventory> thresholdItems;
    private Map<String, Set<LazadaOrderPayments>> lazadaMap;
    private Map<String, Set<ShopeeOrderPayments>> shopeeMap;


    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public List<Inventory> getThresholdItems() {
        return thresholdItems;
    }

    public void setThresholdItems(List<Inventory> thresholdItems) {
        this.thresholdItems = thresholdItems;
    }

    public Map<String, Set<LazadaOrderPayments>> getLazadaMap() {
        return lazadaMap;
    }

    public void setLazadaMap(Map<String, Set<LazadaOrderPayments>> lazadaMap) {
        this.lazadaMap = lazadaMap;
    }

    public Map<String, Set<ShopeeOrderPayments>> getShopeeMap() {
        return shopeeMap;
    }

    public void setShopeeMap(Map<String, Set<ShopeeOrderPayments>> shopeeMap) {
        this.shopeeMap = shopeeMap;
    }
}
