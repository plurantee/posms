package com.flogramming.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Inventory.
 */
@Entity
@Table(name = "inventory")
public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "sku")
    private String sku;

    @Column(name = "stocks")
    private Integer stocks;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "price")
    private Double price;

    @Column(name = "threshold")
    private Integer threshold;

    @OneToMany(mappedBy = "inventory")
    @JsonIgnoreProperties(value = { "payments", "inventory", "client", "shop" }, allowSetters = true)
    private Set<LazadaOrder> lazadaOrders = new HashSet<>();

    @OneToMany(mappedBy = "inventory")
    @JsonIgnoreProperties(value = { "payments", "inventory", "client", "shop" }, allowSetters = true)
    private Set<ShopeeOrder> shopeeOrders = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "inventories", "userInfos", "shops", "lazadaOrders", "shopeeOrders" }, allowSetters = true)
    private Client client;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Inventory id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return this.sku;
    }

    public Inventory sku(String sku) {
        this.setSku(sku);
        return this;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getStocks() {
        return this.stocks;
    }

    public Inventory stocks(Integer stocks) {
        this.setStocks(stocks);
        return this;
    }

    public void setStocks(Integer stocks) {
        this.stocks = stocks;
    }

    public Double getCost() {
        return this.cost;
    }

    public Inventory cost(Double cost) {
        this.setCost(cost);
        return this;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getPrice() {
        return this.price;
    }

    public Inventory price(Double price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getThreshold() {
        return this.threshold;
    }

    public Inventory threshold(Integer threshold) {
        this.setThreshold(threshold);
        return this;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public Set<LazadaOrder> getLazadaOrders() {
        return this.lazadaOrders;
    }

    public void setLazadaOrders(Set<LazadaOrder> lazadaOrders) {
        if (this.lazadaOrders != null) {
            this.lazadaOrders.forEach(i -> i.setInventory(null));
        }
        if (lazadaOrders != null) {
            lazadaOrders.forEach(i -> i.setInventory(this));
        }
        this.lazadaOrders = lazadaOrders;
    }

    public Inventory lazadaOrders(Set<LazadaOrder> lazadaOrders) {
        this.setLazadaOrders(lazadaOrders);
        return this;
    }

    public Inventory addLazadaOrders(LazadaOrder lazadaOrder) {
        this.lazadaOrders.add(lazadaOrder);
        lazadaOrder.setInventory(this);
        return this;
    }

    public Inventory removeLazadaOrders(LazadaOrder lazadaOrder) {
        this.lazadaOrders.remove(lazadaOrder);
        lazadaOrder.setInventory(null);
        return this;
    }

    public Set<ShopeeOrder> getShopeeOrders() {
        return this.shopeeOrders;
    }

    public void setShopeeOrders(Set<ShopeeOrder> shopeeOrders) {
        if (this.shopeeOrders != null) {
            this.shopeeOrders.forEach(i -> i.setInventory(null));
        }
        if (shopeeOrders != null) {
            shopeeOrders.forEach(i -> i.setInventory(this));
        }
        this.shopeeOrders = shopeeOrders;
    }

    public Inventory shopeeOrders(Set<ShopeeOrder> shopeeOrders) {
        this.setShopeeOrders(shopeeOrders);
        return this;
    }

    public Inventory addShopeeOrders(ShopeeOrder shopeeOrder) {
        this.shopeeOrders.add(shopeeOrder);
        shopeeOrder.setInventory(this);
        return this;
    }

    public Inventory removeShopeeOrders(ShopeeOrder shopeeOrder) {
        this.shopeeOrders.remove(shopeeOrder);
        shopeeOrder.setInventory(null);
        return this;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Inventory client(Client client) {
        this.setClient(client);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Inventory)) {
            return false;
        }
        return id != null && id.equals(((Inventory) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Inventory{" +
            "id=" + getId() +
            ", sku='" + getSku() + "'" +
            ", stocks=" + getStocks() +
            ", cost=" + getCost() +
            ", price=" + getPrice() +
            ", threshold=" + getThreshold() +
            "}";
    }
}
