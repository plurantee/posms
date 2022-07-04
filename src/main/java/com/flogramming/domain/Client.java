package com.flogramming.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

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

    @OneToMany(mappedBy = "clientCode")
    @JsonIgnoreProperties(value = { "user", "clientCode" }, allowSetters = true)
    private Set<UserInfo> userInfos = new HashSet<>();

    @OneToMany(mappedBy = "clientCode")
    @JsonIgnoreProperties(value = { "clientCode" }, allowSetters = true)
    private Set<Shop> shops = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Client id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return this.clientName;
    }

    public Client clientName(String clientName) {
        this.setClientName(clientName);
        return this;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientCode() {
        return this.clientCode;
    }

    public Client clientCode(String clientCode) {
        this.setClientCode(clientCode);
        return this;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
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
            "}";
    }
}
