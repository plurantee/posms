package com.flogramming.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A ShopeeOrder.
 */
@Entity
@Table(name = "shopee_order")
public class ShopeeOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "return_refund_status")
    private String returnRefundStatus;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @Column(name = "shipping_option")
    private String shippingOption;

    @Column(name = "shipment_method")
    private String shipmentMethod;

    @Column(name = "estimated_ship_out_date")
    private ZonedDateTime estimatedShipOutDate;

    @Column(name = "ship_time")
    private ZonedDateTime shipTime;

    @Column(name = "order_creation_date")
    private ZonedDateTime orderCreationDate;

    @Column(name = "order_paid_time")
    private ZonedDateTime orderPaidTime;

    @Column(name = "parent_sku_reference_no")
    private String parentSkuReferenceNo;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "sku_reference_no")
    private String skuReferenceNo;

    @Column(name = "variation_name")
    private String variationName;

    @Column(name = "original_price")
    private Double originalPrice;

    @Column(name = "deal_price")
    private Double dealPrice;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "product_subtotal")
    private Double productSubtotal;

    @Column(name = "total_discount")
    private Double totalDiscount;

    @Column(name = "price_discount_from_seller")
    private Double priceDiscountFromSeller;

    @Column(name = "shopee_rebate")
    private Double shopeeRebate;

    @Column(name = "sku_total_weight")
    private String skuTotalWeight;

    @Column(name = "number_of_items_in_order")
    private String numberOfItemsInOrder;

    @Column(name = "order_total_weight")
    private String orderTotalWeight;

    @Column(name = "seller_voucher")
    private Double sellerVoucher;

    @Column(name = "seller_absorbed_coin_cashback")
    private String sellerAbsorbedCoinCashback;

    @Column(name = "shopee_voucher")
    private Double shopeeVoucher;

    @Column(name = "bundle_deals_indicator_yn")
    private String bundleDealsIndicatorYN;

    @Column(name = "shopee_bundle_discount")
    private Double shopeeBundleDiscount;

    @Column(name = "seller_bundle_discount")
    private Double sellerBundleDiscount;

    @Column(name = "shopee_coins_offset")
    private Double shopeeCoinsOffset;

    @Column(name = "credit_card_discount_total")
    private Double creditCardDiscountTotal;

    @Column(name = "products_price_paid_by_buyer")
    private Double productsPricePaidByBuyer;

    @Column(name = "buyer_paid_shipping_fee")
    private Double buyerPaidShippingFee;

    @Column(name = "shipping_rebate_estimate")
    private Double shippingRebateEstimate;

    @Column(name = "reverse_shipping_fee")
    private Double reverseShippingFee;

    @Column(name = "service_fee")
    private Double serviceFee;

    @Column(name = "grand_total")
    private Double grandTotal;

    @Column(name = "estimated_shipping_fee")
    private Double estimatedShippingFee;

    @Column(name = "username_buyer")
    private String usernameBuyer;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "town")
    private String town;

    @Column(name = "district")
    private String district;

    @Column(name = "province")
    private String province;

    @Column(name = "region")
    private String region;

    @Column(name = "country")
    private String country;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "remark_from_buyer")
    private String remarkFromBuyer;

    @Column(name = "order_complete_time")
    private ZonedDateTime orderCompleteTime;

    @Column(name = "note")
    private String note;

    @OneToMany(mappedBy = "shopeeOrder")
    @JsonIgnoreProperties(value = { "shopeeOrder" }, allowSetters = true)
    private Set<ShopeeOrderPayments> payments = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "lazadaOrders", "shopeeOrders", "client" }, allowSetters = true)
    private Inventory inventory;

    @ManyToOne
    @JsonIgnoreProperties(value = { "inventories", "userInfos", "shops", "lazadaOrders", "shopeeOrders" }, allowSetters = true)
    private Client client;

    @ManyToOne
    @JsonIgnoreProperties(value = { "lazadaOrders", "shopeeOrders", "clientCode" }, allowSetters = true)
    private Shop shop;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ShopeeOrder id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public ShopeeOrder orderId(String orderId) {
        this.setOrderId(orderId);
        return this;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    public ShopeeOrder orderStatus(String orderStatus) {
        this.setOrderStatus(orderStatus);
        return this;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getReturnRefundStatus() {
        return this.returnRefundStatus;
    }

    public ShopeeOrder returnRefundStatus(String returnRefundStatus) {
        this.setReturnRefundStatus(returnRefundStatus);
        return this;
    }

    public void setReturnRefundStatus(String returnRefundStatus) {
        this.returnRefundStatus = returnRefundStatus;
    }

    public String getTrackingNumber() {
        return this.trackingNumber;
    }

    public ShopeeOrder trackingNumber(String trackingNumber) {
        this.setTrackingNumber(trackingNumber);
        return this;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getShippingOption() {
        return this.shippingOption;
    }

    public ShopeeOrder shippingOption(String shippingOption) {
        this.setShippingOption(shippingOption);
        return this;
    }

    public void setShippingOption(String shippingOption) {
        this.shippingOption = shippingOption;
    }

    public String getShipmentMethod() {
        return this.shipmentMethod;
    }

    public ShopeeOrder shipmentMethod(String shipmentMethod) {
        this.setShipmentMethod(shipmentMethod);
        return this;
    }

    public void setShipmentMethod(String shipmentMethod) {
        this.shipmentMethod = shipmentMethod;
    }

    public ZonedDateTime getEstimatedShipOutDate() {
        return this.estimatedShipOutDate;
    }

    public ShopeeOrder estimatedShipOutDate(ZonedDateTime estimatedShipOutDate) {
        this.setEstimatedShipOutDate(estimatedShipOutDate);
        return this;
    }

    public void setEstimatedShipOutDate(ZonedDateTime estimatedShipOutDate) {
        this.estimatedShipOutDate = estimatedShipOutDate;
    }

    public ZonedDateTime getShipTime() {
        return this.shipTime;
    }

    public ShopeeOrder shipTime(ZonedDateTime shipTime) {
        this.setShipTime(shipTime);
        return this;
    }

    public void setShipTime(ZonedDateTime shipTime) {
        this.shipTime = shipTime;
    }

    public ZonedDateTime getOrderCreationDate() {
        return this.orderCreationDate;
    }

    public ShopeeOrder orderCreationDate(ZonedDateTime orderCreationDate) {
        this.setOrderCreationDate(orderCreationDate);
        return this;
    }

    public void setOrderCreationDate(ZonedDateTime orderCreationDate) {
        this.orderCreationDate = orderCreationDate;
    }

    public ZonedDateTime getOrderPaidTime() {
        return this.orderPaidTime;
    }

    public ShopeeOrder orderPaidTime(ZonedDateTime orderPaidTime) {
        this.setOrderPaidTime(orderPaidTime);
        return this;
    }

    public void setOrderPaidTime(ZonedDateTime orderPaidTime) {
        this.orderPaidTime = orderPaidTime;
    }

    public String getParentSkuReferenceNo() {
        return this.parentSkuReferenceNo;
    }

    public ShopeeOrder parentSkuReferenceNo(String parentSkuReferenceNo) {
        this.setParentSkuReferenceNo(parentSkuReferenceNo);
        return this;
    }

    public void setParentSkuReferenceNo(String parentSkuReferenceNo) {
        this.parentSkuReferenceNo = parentSkuReferenceNo;
    }

    public String getProductName() {
        return this.productName;
    }

    public ShopeeOrder productName(String productName) {
        this.setProductName(productName);
        return this;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSkuReferenceNo() {
        return this.skuReferenceNo;
    }

    public ShopeeOrder skuReferenceNo(String skuReferenceNo) {
        this.setSkuReferenceNo(skuReferenceNo);
        return this;
    }

    public void setSkuReferenceNo(String skuReferenceNo) {
        this.skuReferenceNo = skuReferenceNo;
    }

    public String getVariationName() {
        return this.variationName;
    }

    public ShopeeOrder variationName(String variationName) {
        this.setVariationName(variationName);
        return this;
    }

    public void setVariationName(String variationName) {
        this.variationName = variationName;
    }

    public Double getOriginalPrice() {
        return this.originalPrice;
    }

    public ShopeeOrder originalPrice(Double originalPrice) {
        this.setOriginalPrice(originalPrice);
        return this;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getDealPrice() {
        return this.dealPrice;
    }

    public ShopeeOrder dealPrice(Double dealPrice) {
        this.setDealPrice(dealPrice);
        return this;
    }

    public void setDealPrice(Double dealPrice) {
        this.dealPrice = dealPrice;
    }

    public Double getQuantity() {
        return this.quantity;
    }

    public ShopeeOrder quantity(Double quantity) {
        this.setQuantity(quantity);
        return this;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getProductSubtotal() {
        return this.productSubtotal;
    }

    public ShopeeOrder productSubtotal(Double productSubtotal) {
        this.setProductSubtotal(productSubtotal);
        return this;
    }

    public void setProductSubtotal(Double productSubtotal) {
        this.productSubtotal = productSubtotal;
    }

    public Double getTotalDiscount() {
        return this.totalDiscount;
    }

    public ShopeeOrder totalDiscount(Double totalDiscount) {
        this.setTotalDiscount(totalDiscount);
        return this;
    }

    public void setTotalDiscount(Double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public Double getPriceDiscountFromSeller() {
        return this.priceDiscountFromSeller;
    }

    public ShopeeOrder priceDiscountFromSeller(Double priceDiscountFromSeller) {
        this.setPriceDiscountFromSeller(priceDiscountFromSeller);
        return this;
    }

    public void setPriceDiscountFromSeller(Double priceDiscountFromSeller) {
        this.priceDiscountFromSeller = priceDiscountFromSeller;
    }

    public Double getShopeeRebate() {
        return this.shopeeRebate;
    }

    public ShopeeOrder shopeeRebate(Double shopeeRebate) {
        this.setShopeeRebate(shopeeRebate);
        return this;
    }

    public void setShopeeRebate(Double shopeeRebate) {
        this.shopeeRebate = shopeeRebate;
    }

    public String getSkuTotalWeight() {
        return this.skuTotalWeight;
    }

    public ShopeeOrder skuTotalWeight(String skuTotalWeight) {
        this.setSkuTotalWeight(skuTotalWeight);
        return this;
    }

    public void setSkuTotalWeight(String skuTotalWeight) {
        this.skuTotalWeight = skuTotalWeight;
    }

    public String getNumberOfItemsInOrder() {
        return this.numberOfItemsInOrder;
    }

    public ShopeeOrder numberOfItemsInOrder(String numberOfItemsInOrder) {
        this.setNumberOfItemsInOrder(numberOfItemsInOrder);
        return this;
    }

    public void setNumberOfItemsInOrder(String numberOfItemsInOrder) {
        this.numberOfItemsInOrder = numberOfItemsInOrder;
    }

    public String getOrderTotalWeight() {
        return this.orderTotalWeight;
    }

    public ShopeeOrder orderTotalWeight(String orderTotalWeight) {
        this.setOrderTotalWeight(orderTotalWeight);
        return this;
    }

    public void setOrderTotalWeight(String orderTotalWeight) {
        this.orderTotalWeight = orderTotalWeight;
    }

    public Double getSellerVoucher() {
        return this.sellerVoucher;
    }

    public ShopeeOrder sellerVoucher(Double sellerVoucher) {
        this.setSellerVoucher(sellerVoucher);
        return this;
    }

    public void setSellerVoucher(Double sellerVoucher) {
        this.sellerVoucher = sellerVoucher;
    }

    public String getSellerAbsorbedCoinCashback() {
        return this.sellerAbsorbedCoinCashback;
    }

    public ShopeeOrder sellerAbsorbedCoinCashback(String sellerAbsorbedCoinCashback) {
        this.setSellerAbsorbedCoinCashback(sellerAbsorbedCoinCashback);
        return this;
    }

    public void setSellerAbsorbedCoinCashback(String sellerAbsorbedCoinCashback) {
        this.sellerAbsorbedCoinCashback = sellerAbsorbedCoinCashback;
    }

    public Double getShopeeVoucher() {
        return this.shopeeVoucher;
    }

    public ShopeeOrder shopeeVoucher(Double shopeeVoucher) {
        this.setShopeeVoucher(shopeeVoucher);
        return this;
    }

    public void setShopeeVoucher(Double shopeeVoucher) {
        this.shopeeVoucher = shopeeVoucher;
    }

    public String getBundleDealsIndicatorYN() {
        return this.bundleDealsIndicatorYN;
    }

    public ShopeeOrder bundleDealsIndicatorYN(String bundleDealsIndicatorYN) {
        this.setBundleDealsIndicatorYN(bundleDealsIndicatorYN);
        return this;
    }

    public void setBundleDealsIndicatorYN(String bundleDealsIndicatorYN) {
        this.bundleDealsIndicatorYN = bundleDealsIndicatorYN;
    }

    public Double getShopeeBundleDiscount() {
        return this.shopeeBundleDiscount;
    }

    public ShopeeOrder shopeeBundleDiscount(Double shopeeBundleDiscount) {
        this.setShopeeBundleDiscount(shopeeBundleDiscount);
        return this;
    }

    public void setShopeeBundleDiscount(Double shopeeBundleDiscount) {
        this.shopeeBundleDiscount = shopeeBundleDiscount;
    }

    public Double getSellerBundleDiscount() {
        return this.sellerBundleDiscount;
    }

    public ShopeeOrder sellerBundleDiscount(Double sellerBundleDiscount) {
        this.setSellerBundleDiscount(sellerBundleDiscount);
        return this;
    }

    public void setSellerBundleDiscount(Double sellerBundleDiscount) {
        this.sellerBundleDiscount = sellerBundleDiscount;
    }

    public Double getShopeeCoinsOffset() {
        return this.shopeeCoinsOffset;
    }

    public ShopeeOrder shopeeCoinsOffset(Double shopeeCoinsOffset) {
        this.setShopeeCoinsOffset(shopeeCoinsOffset);
        return this;
    }

    public void setShopeeCoinsOffset(Double shopeeCoinsOffset) {
        this.shopeeCoinsOffset = shopeeCoinsOffset;
    }

    public Double getCreditCardDiscountTotal() {
        return this.creditCardDiscountTotal;
    }

    public ShopeeOrder creditCardDiscountTotal(Double creditCardDiscountTotal) {
        this.setCreditCardDiscountTotal(creditCardDiscountTotal);
        return this;
    }

    public void setCreditCardDiscountTotal(Double creditCardDiscountTotal) {
        this.creditCardDiscountTotal = creditCardDiscountTotal;
    }

    public Double getProductsPricePaidByBuyer() {
        return this.productsPricePaidByBuyer;
    }

    public ShopeeOrder productsPricePaidByBuyer(Double productsPricePaidByBuyer) {
        this.setProductsPricePaidByBuyer(productsPricePaidByBuyer);
        return this;
    }

    public void setProductsPricePaidByBuyer(Double productsPricePaidByBuyer) {
        this.productsPricePaidByBuyer = productsPricePaidByBuyer;
    }

    public Double getBuyerPaidShippingFee() {
        return this.buyerPaidShippingFee;
    }

    public ShopeeOrder buyerPaidShippingFee(Double buyerPaidShippingFee) {
        this.setBuyerPaidShippingFee(buyerPaidShippingFee);
        return this;
    }

    public void setBuyerPaidShippingFee(Double buyerPaidShippingFee) {
        this.buyerPaidShippingFee = buyerPaidShippingFee;
    }

    public Double getShippingRebateEstimate() {
        return this.shippingRebateEstimate;
    }

    public ShopeeOrder shippingRebateEstimate(Double shippingRebateEstimate) {
        this.setShippingRebateEstimate(shippingRebateEstimate);
        return this;
    }

    public void setShippingRebateEstimate(Double shippingRebateEstimate) {
        this.shippingRebateEstimate = shippingRebateEstimate;
    }

    public Double getReverseShippingFee() {
        return this.reverseShippingFee;
    }

    public ShopeeOrder reverseShippingFee(Double reverseShippingFee) {
        this.setReverseShippingFee(reverseShippingFee);
        return this;
    }

    public void setReverseShippingFee(Double reverseShippingFee) {
        this.reverseShippingFee = reverseShippingFee;
    }

    public Double getServiceFee() {
        return this.serviceFee;
    }

    public ShopeeOrder serviceFee(Double serviceFee) {
        this.setServiceFee(serviceFee);
        return this;
    }

    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Double getGrandTotal() {
        return this.grandTotal;
    }

    public ShopeeOrder grandTotal(Double grandTotal) {
        this.setGrandTotal(grandTotal);
        return this;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Double getEstimatedShippingFee() {
        return this.estimatedShippingFee;
    }

    public ShopeeOrder estimatedShippingFee(Double estimatedShippingFee) {
        this.setEstimatedShippingFee(estimatedShippingFee);
        return this;
    }

    public void setEstimatedShippingFee(Double estimatedShippingFee) {
        this.estimatedShippingFee = estimatedShippingFee;
    }

    public String getUsernameBuyer() {
        return this.usernameBuyer;
    }

    public ShopeeOrder usernameBuyer(String usernameBuyer) {
        this.setUsernameBuyer(usernameBuyer);
        return this;
    }

    public void setUsernameBuyer(String usernameBuyer) {
        this.usernameBuyer = usernameBuyer;
    }

    public String getReceiverName() {
        return this.receiverName;
    }

    public ShopeeOrder receiverName(String receiverName) {
        this.setReceiverName(receiverName);
        return this;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public ShopeeOrder phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDeliveryAddress() {
        return this.deliveryAddress;
    }

    public ShopeeOrder deliveryAddress(String deliveryAddress) {
        this.setDeliveryAddress(deliveryAddress);
        return this;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getTown() {
        return this.town;
    }

    public ShopeeOrder town(String town) {
        this.setTown(town);
        return this;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getDistrict() {
        return this.district;
    }

    public ShopeeOrder district(String district) {
        this.setDistrict(district);
        return this;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return this.province;
    }

    public ShopeeOrder province(String province) {
        this.setProvince(province);
        return this;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRegion() {
        return this.region;
    }

    public ShopeeOrder region(String region) {
        this.setRegion(region);
        return this;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return this.country;
    }

    public ShopeeOrder country(String country) {
        this.setCountry(country);
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public ShopeeOrder zipCode(String zipCode) {
        this.setZipCode(zipCode);
        return this;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getRemarkFromBuyer() {
        return this.remarkFromBuyer;
    }

    public ShopeeOrder remarkFromBuyer(String remarkFromBuyer) {
        this.setRemarkFromBuyer(remarkFromBuyer);
        return this;
    }

    public void setRemarkFromBuyer(String remarkFromBuyer) {
        this.remarkFromBuyer = remarkFromBuyer;
    }

    public ZonedDateTime getOrderCompleteTime() {
        return this.orderCompleteTime;
    }

    public ShopeeOrder orderCompleteTime(ZonedDateTime orderCompleteTime) {
        this.setOrderCompleteTime(orderCompleteTime);
        return this;
    }

    public void setOrderCompleteTime(ZonedDateTime orderCompleteTime) {
        this.orderCompleteTime = orderCompleteTime;
    }

    public String getNote() {
        return this.note;
    }

    public ShopeeOrder note(String note) {
        this.setNote(note);
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Set<ShopeeOrderPayments> getPayments() {
        return this.payments;
    }

    public void setPayments(Set<ShopeeOrderPayments> shopeeOrderPayments) {
        if (this.payments != null) {
            this.payments.forEach(i -> i.setShopeeOrder(null));
        }
        if (shopeeOrderPayments != null) {
            shopeeOrderPayments.forEach(i -> i.setShopeeOrder(this));
        }
        this.payments = shopeeOrderPayments;
    }

    public ShopeeOrder payments(Set<ShopeeOrderPayments> shopeeOrderPayments) {
        this.setPayments(shopeeOrderPayments);
        return this;
    }

    public ShopeeOrder addPayments(ShopeeOrderPayments shopeeOrderPayments) {
        this.payments.add(shopeeOrderPayments);
        shopeeOrderPayments.setShopeeOrder(this);
        return this;
    }

    public ShopeeOrder removePayments(ShopeeOrderPayments shopeeOrderPayments) {
        this.payments.remove(shopeeOrderPayments);
        shopeeOrderPayments.setShopeeOrder(null);
        return this;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public ShopeeOrder inventory(Inventory inventory) {
        this.setInventory(inventory);
        return this;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ShopeeOrder client(Client client) {
        this.setClient(client);
        return this;
    }

    public Shop getShop() {
        return this.shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public ShopeeOrder shop(Shop shop) {
        this.setShop(shop);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShopeeOrder)) {
            return false;
        }
        return id != null && id.equals(((ShopeeOrder) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ShopeeOrder{" +
            "id=" + getId() +
            ", orderId='" + getOrderId() + "'" +
            ", orderStatus='" + getOrderStatus() + "'" +
            ", returnRefundStatus='" + getReturnRefundStatus() + "'" +
            ", trackingNumber='" + getTrackingNumber() + "'" +
            ", shippingOption='" + getShippingOption() + "'" +
            ", shipmentMethod='" + getShipmentMethod() + "'" +
            ", estimatedShipOutDate='" + getEstimatedShipOutDate() + "'" +
            ", shipTime='" + getShipTime() + "'" +
            ", orderCreationDate='" + getOrderCreationDate() + "'" +
            ", orderPaidTime='" + getOrderPaidTime() + "'" +
            ", parentSkuReferenceNo='" + getParentSkuReferenceNo() + "'" +
            ", productName='" + getProductName() + "'" +
            ", skuReferenceNo='" + getSkuReferenceNo() + "'" +
            ", variationName='" + getVariationName() + "'" +
            ", originalPrice=" + getOriginalPrice() +
            ", dealPrice=" + getDealPrice() +
            ", quantity=" + getQuantity() +
            ", productSubtotal=" + getProductSubtotal() +
            ", totalDiscount=" + getTotalDiscount() +
            ", priceDiscountFromSeller=" + getPriceDiscountFromSeller() +
            ", shopeeRebate=" + getShopeeRebate() +
            ", skuTotalWeight='" + getSkuTotalWeight() + "'" +
            ", numberOfItemsInOrder='" + getNumberOfItemsInOrder() + "'" +
            ", orderTotalWeight='" + getOrderTotalWeight() + "'" +
            ", sellerVoucher=" + getSellerVoucher() +
            ", sellerAbsorbedCoinCashback='" + getSellerAbsorbedCoinCashback() + "'" +
            ", shopeeVoucher=" + getShopeeVoucher() +
            ", bundleDealsIndicatorYN='" + getBundleDealsIndicatorYN() + "'" +
            ", shopeeBundleDiscount=" + getShopeeBundleDiscount() +
            ", sellerBundleDiscount=" + getSellerBundleDiscount() +
            ", shopeeCoinsOffset=" + getShopeeCoinsOffset() +
            ", creditCardDiscountTotal=" + getCreditCardDiscountTotal() +
            ", productsPricePaidByBuyer=" + getProductsPricePaidByBuyer() +
            ", buyerPaidShippingFee=" + getBuyerPaidShippingFee() +
            ", shippingRebateEstimate=" + getShippingRebateEstimate() +
            ", reverseShippingFee=" + getReverseShippingFee() +
            ", serviceFee=" + getServiceFee() +
            ", grandTotal=" + getGrandTotal() +
            ", estimatedShippingFee=" + getEstimatedShippingFee() +
            ", usernameBuyer='" + getUsernameBuyer() + "'" +
            ", receiverName='" + getReceiverName() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", deliveryAddress='" + getDeliveryAddress() + "'" +
            ", town='" + getTown() + "'" +
            ", district='" + getDistrict() + "'" +
            ", province='" + getProvince() + "'" +
            ", region='" + getRegion() + "'" +
            ", country='" + getCountry() + "'" +
            ", zipCode='" + getZipCode() + "'" +
            ", remarkFromBuyer='" + getRemarkFromBuyer() + "'" +
            ", orderCompleteTime='" + getOrderCompleteTime() + "'" +
            ", note='" + getNote() + "'" +
            "}";
    }
}
