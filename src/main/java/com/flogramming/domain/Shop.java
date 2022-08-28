package com.flogramming.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.flogramming.domain.enumeration.ShopType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Shop.
 */
@Entity
@Table(name = "shop")
public class Shop implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "shop_code")
    private String shopCode;

    @Column(name = "shop_name")
    private String shopName;

    @Enumerated(EnumType.STRING)
    @Column(name = "shop_type")
    private ShopType shopType;

    @OneToMany(mappedBy = "shop")
    @JsonIgnoreProperties(value = { "payments", "inventory", "client", "shop" }, allowSetters = true)
    private Set<LazadaOrder> lazadaOrders = new HashSet<>();

    @OneToMany(mappedBy = "shop")
    @JsonIgnoreProperties(value = { "payments", "inventory", "client", "shop" }, allowSetters = true)
    private Set<ShopeeOrder> shopeeOrders = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "inventories", "userInfos", "shops", "lazadaOrders", "shopeeOrders" }, allowSetters = true)
    private Client clientCode;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Shop id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopCode() {
        return this.shopCode;
    }

    public Shop shopCode(String shopCode) {
        this.setShopCode(shopCode);
        return this;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getShopName() {
        return this.shopName;
    }

    public Shop shopName(String shopName) {
        this.setShopName(shopName);
        return this;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public ShopType getShopType() {
        return this.shopType;
    }

    public Shop shopType(ShopType shopType) {
        this.setShopType(shopType);
        return this;
    }

    public void setShopType(ShopType shopType) {
        this.shopType = shopType;
    }

    public Set<LazadaOrder> getLazadaOrders() {
        return this.lazadaOrders;
    }

    public void setLazadaOrders(Set<LazadaOrder> lazadaOrders) {
        if (this.lazadaOrders != null) {
            this.lazadaOrders.forEach(i -> i.setShop(null));
        }
        if (lazadaOrders != null) {
            lazadaOrders.forEach(i -> i.setShop(this));
        }
        this.lazadaOrders = lazadaOrders;
    }

    public Shop lazadaOrders(Set<LazadaOrder> lazadaOrders) {
        this.setLazadaOrders(lazadaOrders);
        return this;
    }

    public Shop addLazadaOrder(LazadaOrder lazadaOrder) {
        this.lazadaOrders.add(lazadaOrder);
        lazadaOrder.setShop(this);
        return this;
    }

    public Shop removeLazadaOrder(LazadaOrder lazadaOrder) {
        this.lazadaOrders.remove(lazadaOrder);
        lazadaOrder.setShop(null);
        return this;
    }

    public Set<ShopeeOrder> getShopeeOrders() {
        return this.shopeeOrders;
    }

    public void setShopeeOrders(Set<ShopeeOrder> shopeeOrders) {
        if (this.shopeeOrders != null) {
            this.shopeeOrders.forEach(i -> i.setShop(null));
        }
        if (shopeeOrders != null) {
            shopeeOrders.forEach(i -> i.setShop(this));
        }
        this.shopeeOrders = shopeeOrders;
    }

    public Shop shopeeOrders(Set<ShopeeOrder> shopeeOrders) {
        this.setShopeeOrders(shopeeOrders);
        return this;
    }

    public Shop addShopeeOrder(ShopeeOrder shopeeOrder) {
        this.shopeeOrders.add(shopeeOrder);
        shopeeOrder.setShop(this);
        return this;
    }

    public Shop removeShopeeOrder(ShopeeOrder shopeeOrder) {
        this.shopeeOrders.remove(shopeeOrder);
        shopeeOrder.setShop(null);
        return this;
    }

    public Client getClientCode() {
        return this.clientCode;
    }

    public void setClientCode(Client client) {
        this.clientCode = client;
    }

    public Shop clientCode(Client client) {
        this.setClientCode(client);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Shop)) {
            return false;
        }
        return id != null && id.equals(((Shop) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Shop{" +
            "id=" + getId() +
            ", shopCode='" + getShopCode() + "'" +
            ", shopName='" + getShopName() + "'" +
            ", shopType='" + getShopType() + "'" +
            "}";
    }
}
