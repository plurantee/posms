package com.flogramming.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.flogramming.domain.enumeration.ShopType;
import java.io.Serializable;
import javax.persistence.*;

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

    @ManyToOne
    @JsonIgnoreProperties(value = { "userInfos", "shops" }, allowSetters = true)
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
