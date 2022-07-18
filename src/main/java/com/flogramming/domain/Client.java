package com.flogramming.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.flogramming.domain.enumeration.ClientType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A Client.
 */
@Entity
@Table(name = "client")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_code")
    private String clientCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "client_type")
    private ClientType clientType;

    @Column(name = "validity_date")
    private ZonedDateTime validityDate;

    @OneToMany(mappedBy = "clientCode")
    @JsonIgnoreProperties(value = {"user", "clientCode"}, allowSetters = true)
    private Set<UserInfo> userInfos = new HashSet<>();

    @OneToMany(mappedBy = "clientCode")
    @JsonIgnoreProperties(value = {"lazadaOrders", "shopeeOrders", "clientCode"}, allowSetters = true)
    private Set<Shop> shops = new HashSet<>();

    @OneToMany(mappedBy = "client")
    @JsonIgnoreProperties(value = {"payments", "client", "shop"}, allowSetters = true)
    private Set<LazadaOrder> lazadaOrders = new HashSet<>();

    @OneToMany(mappedBy = "client")
    @JsonIgnoreProperties(value = {"client", "shop"}, allowSetters = true)
    private Set<ShopeeOrder> shopeeOrders = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client id(Long id) {
        this.setId(id);
        return this;
    }

    public String getClientName() {
        return this.clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Client clientName(String clientName) {
        this.setClientName(clientName);
        return this;
    }

    public String getClientCode() {
        return this.clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public Client clientCode(String clientCode) {
        this.setClientCode(clientCode);
        return this;
    }

    public ClientType getClientType() {
        return this.clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public Client clientType(ClientType clientType) {
        this.setClientType(clientType);
        return this;
    }

    public ZonedDateTime getValidityDate() {
        return this.validityDate;
    }

    public void setValidityDate(ZonedDateTime validityDate) {
        this.validityDate = validityDate;
    }

    public Client validityDate(ZonedDateTime validityDate) {
        this.setValidityDate(validityDate);
        return this;
    }

    public Set<UserInfo> getUserInfos() {
        return this.userInfos;
    }

    public void setUserInfos(Set<UserInfo> userInfos) {
        if (this.userInfos != null) {
            this.userInfos.forEach(i -> i.setClientCode(null));
        }
        if (userInfos != null) {
            userInfos.forEach(i -> i.setClientCode(this));
        }
        this.userInfos = userInfos;
    }

    public Client userInfos(Set<UserInfo> userInfos) {
        this.setUserInfos(userInfos);
        return this;
    }

    public Client addUserInfo(UserInfo userInfo) {
        this.userInfos.add(userInfo);
        userInfo.setClientCode(this);
        return this;
    }

    public Client removeUserInfo(UserInfo userInfo) {
        this.userInfos.remove(userInfo);
        userInfo.setClientCode(null);
        return this;
    }

    public Set<Shop> getShops() {
        return this.shops;
    }

    public void setShops(Set<Shop> shops) {
        if (this.shops != null) {
            this.shops.forEach(i -> i.setClientCode(null));
        }
        if (shops != null) {
            shops.forEach(i -> i.setClientCode(this));
        }
        this.shops = shops;
    }

    public Client shops(Set<Shop> shops) {
        this.setShops(shops);
        return this;
    }

    public Client addShop(Shop shop) {
        this.shops.add(shop);
        shop.setClientCode(this);
        return this;
    }

    public Client removeShop(Shop shop) {
        this.shops.remove(shop);
        shop.setClientCode(null);
        return this;
    }

    public Set<LazadaOrder> getLazadaOrders() {
        return this.lazadaOrders;
    }

    public void setLazadaOrders(Set<LazadaOrder> lazadaOrders) {
        if (this.lazadaOrders != null) {
            this.lazadaOrders.forEach(i -> i.setClient(null));
        }
        if (lazadaOrders != null) {
            lazadaOrders.forEach(i -> i.setClient(this));
        }
        this.lazadaOrders = lazadaOrders;
    }

    public Client lazadaOrders(Set<LazadaOrder> lazadaOrders) {
        this.setLazadaOrders(lazadaOrders);
        return this;
    }

    public Client addLazadaOrder(LazadaOrder lazadaOrder) {
        this.lazadaOrders.add(lazadaOrder);
        lazadaOrder.setClient(this);
        return this;
    }

    public Client removeLazadaOrder(LazadaOrder lazadaOrder) {
        this.lazadaOrders.remove(lazadaOrder);
        lazadaOrder.setClient(null);
        return this;
    }

    public Set<ShopeeOrder> getShopeeOrders() {
        return this.shopeeOrders;
    }

    public void setShopeeOrders(Set<ShopeeOrder> shopeeOrders) {
        if (this.shopeeOrders != null) {
            this.shopeeOrders.forEach(i -> i.setClient(null));
        }
        if (shopeeOrders != null) {
            shopeeOrders.forEach(i -> i.setClient(this));
        }
        this.shopeeOrders = shopeeOrders;
    }

    public Client shopeeOrders(Set<ShopeeOrder> shopeeOrders) {
        this.setShopeeOrders(shopeeOrders);
        return this;
    }

    public Client addShopeeOrder(ShopeeOrder shopeeOrder) {
        this.shopeeOrders.add(shopeeOrder);
        shopeeOrder.setClient(this);
        return this;
    }

    public Client removeShopeeOrder(ShopeeOrder shopeeOrder) {
        this.shopeeOrders.remove(shopeeOrder);
        shopeeOrder.setClient(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Client)) {
            return false;
        }
        return id != null && id.equals(((Client) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Client{" +
            "id=" + getId() +
            ", clientName='" + getClientName() + "'" +
            ", clientCode='" + getClientCode() + "'" +
            ", clientType='" + getClientType() + "'" +
            ", validityDate='" + getValidityDate() + "'" +
            "}";
    }
}
