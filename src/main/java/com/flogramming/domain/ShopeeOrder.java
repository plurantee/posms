package com.flogramming.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.ZonedDateTime;

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

    @ManyToOne
    @JsonIgnoreProperties(value = {"userInfos", "shops", "lazadaOrders", "shopeeOrders"}, allowSetters = true)
    private Client client;

    @ManyToOne
    @JsonIgnoreProperties(value = {"lazadaOrders", "shopeeOrders", "clientCode"}, allowSetters = true)
    private Shop shop;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShopeeOrder id(Long id) {
        this.setId(id);
        return this;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public ShopeeOrder orderId(String orderId) {
        this.setOrderId(orderId);
        return this;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public ShopeeOrder orderStatus(String orderStatus) {
        this.setOrderStatus(orderStatus);
        return this;
    }

    public String getReturnRefundStatus() {
        return this.returnRefundStatus;
    }

    public void setReturnRefundStatus(String returnRefundStatus) {
        this.returnRefundStatus = returnRefundStatus;
    }

    public ShopeeOrder returnRefundStatus(String returnRefundStatus) {
        this.setReturnRefundStatus(returnRefundStatus);
        return this;
    }

    public String getTrackingNumber() {
        return this.trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public ShopeeOrder trackingNumber(String trackingNumber) {
        this.setTrackingNumber(trackingNumber);
        return this;
    }

    public String getShippingOption() {
        return this.shippingOption;
    }

    public void setShippingOption(String shippingOption) {
        this.shippingOption = shippingOption;
    }

    public ShopeeOrder shippingOption(String shippingOption) {
        this.setShippingOption(shippingOption);
        return this;
    }

    public String getShipmentMethod() {
        return this.shipmentMethod;
    }

    public void setShipmentMethod(String shipmentMethod) {
        this.shipmentMethod = shipmentMethod;
    }

    public ShopeeOrder shipmentMethod(String shipmentMethod) {
        this.setShipmentMethod(shipmentMethod);
        return this;
    }

    public ZonedDateTime getEstimatedShipOutDate() {
        return this.estimatedShipOutDate;
    }

    public void setEstimatedShipOutDate(ZonedDateTime estimatedShipOutDate) {
        this.estimatedShipOutDate = estimatedShipOutDate;
    }

    public ShopeeOrder estimatedShipOutDate(ZonedDateTime estimatedShipOutDate) {
        this.setEstimatedShipOutDate(estimatedShipOutDate);
        return this;
    }

    public ZonedDateTime getShipTime() {
        return this.shipTime;
    }

    public void setShipTime(ZonedDateTime shipTime) {
        this.shipTime = shipTime;
    }

    public ShopeeOrder shipTime(ZonedDateTime shipTime) {
        this.setShipTime(shipTime);
        return this;
    }

    public ZonedDateTime getOrderCreationDate() {
        return this.orderCreationDate;
    }

    public void setOrderCreationDate(ZonedDateTime orderCreationDate) {
        this.orderCreationDate = orderCreationDate;
    }

    public ShopeeOrder orderCreationDate(ZonedDateTime orderCreationDate) {
        this.setOrderCreationDate(orderCreationDate);
        return this;
    }

    public ZonedDateTime getOrderPaidTime() {
        return this.orderPaidTime;
    }

    public void setOrderPaidTime(ZonedDateTime orderPaidTime) {
        this.orderPaidTime = orderPaidTime;
    }

    public ShopeeOrder orderPaidTime(ZonedDateTime orderPaidTime) {
        this.setOrderPaidTime(orderPaidTime);
        return this;
    }

    public String getParentSkuReferenceNo() {
        return this.parentSkuReferenceNo;
    }

    public void setParentSkuReferenceNo(String parentSkuReferenceNo) {
        this.parentSkuReferenceNo = parentSkuReferenceNo;
    }

    public ShopeeOrder parentSkuReferenceNo(String parentSkuReferenceNo) {
        this.setParentSkuReferenceNo(parentSkuReferenceNo);
        return this;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ShopeeOrder productName(String productName) {
        this.setProductName(productName);
        return this;
    }

    public String getSkuReferenceNo() {
        return this.skuReferenceNo;
    }

    public void setSkuReferenceNo(String skuReferenceNo) {
        this.skuReferenceNo = skuReferenceNo;
    }

    public ShopeeOrder skuReferenceNo(String skuReferenceNo) {
        this.setSkuReferenceNo(skuReferenceNo);
        return this;
    }

    public String getVariationName() {
        return this.variationName;
    }

    public void setVariationName(String variationName) {
        this.variationName = variationName;
    }

    public ShopeeOrder variationName(String variationName) {
        this.setVariationName(variationName);
        return this;
    }

    public Double getOriginalPrice() {
        return this.originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public ShopeeOrder originalPrice(Double originalPrice) {
        this.setOriginalPrice(originalPrice);
        return this;
    }

    public Double getDealPrice() {
        return this.dealPrice;
    }

    public void setDealPrice(Double dealPrice) {
        this.dealPrice = dealPrice;
    }

    public ShopeeOrder dealPrice(Double dealPrice) {
        this.setDealPrice(dealPrice);
        return this;
    }

    public Double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public ShopeeOrder quantity(Double quantity) {
        this.setQuantity(quantity);
        return this;
    }

    public Double getProductSubtotal() {
        return this.productSubtotal;
    }

    public void setProductSubtotal(Double productSubtotal) {
        this.productSubtotal = productSubtotal;
    }

    public ShopeeOrder productSubtotal(Double productSubtotal) {
        this.setProductSubtotal(productSubtotal);
        return this;
    }

    public Double getTotalDiscount() {
        return this.totalDiscount;
    }

    public void setTotalDiscount(Double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public ShopeeOrder totalDiscount(Double totalDiscount) {
        this.setTotalDiscount(totalDiscount);
        return this;
    }

    public Double getPriceDiscountFromSeller() {
        return this.priceDiscountFromSeller;
    }

    public void setPriceDiscountFromSeller(Double priceDiscountFromSeller) {
        this.priceDiscountFromSeller = priceDiscountFromSeller;
    }

    public ShopeeOrder priceDiscountFromSeller(Double priceDiscountFromSeller) {
        this.setPriceDiscountFromSeller(priceDiscountFromSeller);
        return this;
    }

    public Double getShopeeRebate() {
        return this.shopeeRebate;
    }

    public void setShopeeRebate(Double shopeeRebate) {
        this.shopeeRebate = shopeeRebate;
    }

    public ShopeeOrder shopeeRebate(Double shopeeRebate) {
        this.setShopeeRebate(shopeeRebate);
        return this;
    }

    public String getSkuTotalWeight() {
        return this.skuTotalWeight;
    }

    public void setSkuTotalWeight(String skuTotalWeight) {
        this.skuTotalWeight = skuTotalWeight;
    }

    public ShopeeOrder skuTotalWeight(String skuTotalWeight) {
        this.setSkuTotalWeight(skuTotalWeight);
        return this;
    }

    public String getNumberOfItemsInOrder() {
        return this.numberOfItemsInOrder;
    }

    public void setNumberOfItemsInOrder(String numberOfItemsInOrder) {
        this.numberOfItemsInOrder = numberOfItemsInOrder;
    }

    public ShopeeOrder numberOfItemsInOrder(String numberOfItemsInOrder) {
        this.setNumberOfItemsInOrder(numberOfItemsInOrder);
        return this;
    }

    public String getOrderTotalWeight() {
        return this.orderTotalWeight;
    }

    public void setOrderTotalWeight(String orderTotalWeight) {
        this.orderTotalWeight = orderTotalWeight;
    }

    public ShopeeOrder orderTotalWeight(String orderTotalWeight) {
        this.setOrderTotalWeight(orderTotalWeight);
        return this;
    }

    public Double getSellerVoucher() {
        return this.sellerVoucher;
    }

    public void setSellerVoucher(Double sellerVoucher) {
        this.sellerVoucher = sellerVoucher;
    }

    public ShopeeOrder sellerVoucher(Double sellerVoucher) {
        this.setSellerVoucher(sellerVoucher);
        return this;
    }

    public String getSellerAbsorbedCoinCashback() {
        return this.sellerAbsorbedCoinCashback;
    }

    public void setSellerAbsorbedCoinCashback(String sellerAbsorbedCoinCashback) {
        this.sellerAbsorbedCoinCashback = sellerAbsorbedCoinCashback;
    }

    public ShopeeOrder sellerAbsorbedCoinCashback(String sellerAbsorbedCoinCashback) {
        this.setSellerAbsorbedCoinCashback(sellerAbsorbedCoinCashback);
        return this;
    }

    public Double getShopeeVoucher() {
        return this.shopeeVoucher;
    }

    public void setShopeeVoucher(Double shopeeVoucher) {
        this.shopeeVoucher = shopeeVoucher;
    }

    public ShopeeOrder shopeeVoucher(Double shopeeVoucher) {
        this.setShopeeVoucher(shopeeVoucher);
        return this;
    }

    public String getBundleDealsIndicatorYN() {
        return this.bundleDealsIndicatorYN;
    }

    public void setBundleDealsIndicatorYN(String bundleDealsIndicatorYN) {
        this.bundleDealsIndicatorYN = bundleDealsIndicatorYN;
    }

    public ShopeeOrder bundleDealsIndicatorYN(String bundleDealsIndicatorYN) {
        this.setBundleDealsIndicatorYN(bundleDealsIndicatorYN);
        return this;
    }

    public Double getShopeeBundleDiscount() {
        return this.shopeeBundleDiscount;
    }

    public void setShopeeBundleDiscount(Double shopeeBundleDiscount) {
        this.shopeeBundleDiscount = shopeeBundleDiscount;
    }

    public ShopeeOrder shopeeBundleDiscount(Double shopeeBundleDiscount) {
        this.setShopeeBundleDiscount(shopeeBundleDiscount);
        return this;
    }

    public Double getSellerBundleDiscount() {
        return this.sellerBundleDiscount;
    }

    public void setSellerBundleDiscount(Double sellerBundleDiscount) {
        this.sellerBundleDiscount = sellerBundleDiscount;
    }

    public ShopeeOrder sellerBundleDiscount(Double sellerBundleDiscount) {
        this.setSellerBundleDiscount(sellerBundleDiscount);
        return this;
    }

    public Double getShopeeCoinsOffset() {
        return this.shopeeCoinsOffset;
    }

    public void setShopeeCoinsOffset(Double shopeeCoinsOffset) {
        this.shopeeCoinsOffset = shopeeCoinsOffset;
    }

    public ShopeeOrder shopeeCoinsOffset(Double shopeeCoinsOffset) {
        this.setShopeeCoinsOffset(shopeeCoinsOffset);
        return this;
    }

    public Double getCreditCardDiscountTotal() {
        return this.creditCardDiscountTotal;
    }

    public void setCreditCardDiscountTotal(Double creditCardDiscountTotal) {
        this.creditCardDiscountTotal = creditCardDiscountTotal;
    }

    public ShopeeOrder creditCardDiscountTotal(Double creditCardDiscountTotal) {
        this.setCreditCardDiscountTotal(creditCardDiscountTotal);
        return this;
    }

    public Double getProductsPricePaidByBuyer() {
        return this.productsPricePaidByBuyer;
    }

    public void setProductsPricePaidByBuyer(Double productsPricePaidByBuyer) {
        this.productsPricePaidByBuyer = productsPricePaidByBuyer;
    }

    public ShopeeOrder productsPricePaidByBuyer(Double productsPricePaidByBuyer) {
        this.setProductsPricePaidByBuyer(productsPricePaidByBuyer);
        return this;
    }

    public Double getBuyerPaidShippingFee() {
        return this.buyerPaidShippingFee;
    }

    public void setBuyerPaidShippingFee(Double buyerPaidShippingFee) {
        this.buyerPaidShippingFee = buyerPaidShippingFee;
    }

    public ShopeeOrder buyerPaidShippingFee(Double buyerPaidShippingFee) {
        this.setBuyerPaidShippingFee(buyerPaidShippingFee);
        return this;
    }

    public Double getShippingRebateEstimate() {
        return this.shippingRebateEstimate;
    }

    public void setShippingRebateEstimate(Double shippingRebateEstimate) {
        this.shippingRebateEstimate = shippingRebateEstimate;
    }

    public ShopeeOrder shippingRebateEstimate(Double shippingRebateEstimate) {
        this.setShippingRebateEstimate(shippingRebateEstimate);
        return this;
    }

    public Double getReverseShippingFee() {
        return this.reverseShippingFee;
    }

    public void setReverseShippingFee(Double reverseShippingFee) {
        this.reverseShippingFee = reverseShippingFee;
    }

    public ShopeeOrder reverseShippingFee(Double reverseShippingFee) {
        this.setReverseShippingFee(reverseShippingFee);
        return this;
    }

    public Double getServiceFee() {
        return this.serviceFee;
    }

    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public ShopeeOrder serviceFee(Double serviceFee) {
        this.setServiceFee(serviceFee);
        return this;
    }

    public Double getGrandTotal() {
        return this.grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public ShopeeOrder grandTotal(Double grandTotal) {
        this.setGrandTotal(grandTotal);
        return this;
    }

    public Double getEstimatedShippingFee() {
        return this.estimatedShippingFee;
    }

    public void setEstimatedShippingFee(Double estimatedShippingFee) {
        this.estimatedShippingFee = estimatedShippingFee;
    }

    public ShopeeOrder estimatedShippingFee(Double estimatedShippingFee) {
        this.setEstimatedShippingFee(estimatedShippingFee);
        return this;
    }

    public String getUsernameBuyer() {
        return this.usernameBuyer;
    }

    public void setUsernameBuyer(String usernameBuyer) {
        this.usernameBuyer = usernameBuyer;
    }

    public ShopeeOrder usernameBuyer(String usernameBuyer) {
        this.setUsernameBuyer(usernameBuyer);
        return this;
    }

    public String getReceiverName() {
        return this.receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public ShopeeOrder receiverName(String receiverName) {
        this.setReceiverName(receiverName);
        return this;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ShopeeOrder phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public String getDeliveryAddress() {
        return this.deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public ShopeeOrder deliveryAddress(String deliveryAddress) {
        this.setDeliveryAddress(deliveryAddress);
        return this;
    }

    public String getTown() {
        return this.town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public ShopeeOrder town(String town) {
        this.setTown(town);
        return this;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public ShopeeOrder district(String district) {
        this.setDistrict(district);
        return this;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public ShopeeOrder province(String province) {
        this.setProvince(province);
        return this;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public ShopeeOrder region(String region) {
        this.setRegion(region);
        return this;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ShopeeOrder country(String country) {
        this.setCountry(country);
        return this;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public ShopeeOrder zipCode(String zipCode) {
        this.setZipCode(zipCode);
        return this;
    }

    public String getRemarkFromBuyer() {
        return this.remarkFromBuyer;
    }

    public void setRemarkFromBuyer(String remarkFromBuyer) {
        this.remarkFromBuyer = remarkFromBuyer;
    }

    public ShopeeOrder remarkFromBuyer(String remarkFromBuyer) {
        this.setRemarkFromBuyer(remarkFromBuyer);
        return this;
    }

    public ZonedDateTime getOrderCompleteTime() {
        return this.orderCompleteTime;
    }

    public void setOrderCompleteTime(ZonedDateTime orderCompleteTime) {
        this.orderCompleteTime = orderCompleteTime;
    }

    public ShopeeOrder orderCompleteTime(ZonedDateTime orderCompleteTime) {
        this.setOrderCompleteTime(orderCompleteTime);
        return this;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ShopeeOrder note(String note) {
        this.setNote(note);
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
